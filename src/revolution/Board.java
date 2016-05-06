package revolution;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import revolution.entities.AreaInfo;
import revolution.entities.ChoiceInfo;
import revolution.entities.GameData;
import revolution.entities.Reward;
import revolution.enums.AreaName;
import revolution.enums.Choice;
import revolution.enums.RewardType;
import revolution.enums.State;
import revolution.enums.TokenType;
import revolution.exceptions.RevolutionGameException;


public class Board {
	private static final int minimumTokens = 5;
	
	private GameData gameData;

	
	public Board() {
		gameData = new GameData();
	}
	

		
	public void joinGame(int playerId) {
		gameData.getPlayers().put(playerId,  new Player(playerId));
	}

	public GameData PlayerSubmission(int playerId, Map<Choice, HashMap<TokenType, Integer>> decision) throws RevolutionGameException {

		if(gameData.getGameState() == State.Finished) {
			throw new RevolutionGameException("Game already finished");
		}
		
		Player player = gameData.getPlayers().get(playerId);
		
		if (player == null)
			throw new RevolutionGameException("Player not found");

		player.checkAndSetDecision(decision, gameData.getChoices());
		
		if (lastPlayer()) {
			turnFinished();
			this.gameData.setRound(this.gameData.getRound() + 1);
			notifyPlayers();
			
		}
		return gameData;
	}
	
	public int submittedPlayers(){
		int count = 0;
		for(Player player : gameData.getPlayers().values()){
			if(player.getDecisions() != null){
				count++;
			}
		}
		if(count == 0){
			return count= gameData.getPlayers().size();
		}
		return count;
	}

	private boolean lastPlayer() {
		for (Player player : gameData.getPlayers().values()) {
			if (player.getDecisions() == null)
				return false;
		}		
		return true;
	}

	/**
	 * for each choice
		    decide which player won the bet
		     sum player reward to player token map
		     sum player reward points 
		     if areas sum total < max:
		           sum player areas cubes ++
		           area sum total cubes ++ 
		 for each player
		    token map should be at least 5 tokens if not add gold
		 	check if the game is finished
	 * @throws RevolutionGameException 
	 */
	public void turnFinished() throws RevolutionGameException {
		 // new token map for each player		
		for(Player player : gameData.getPlayers().values()) {
			player.setToken(new HashMap<TokenType, Integer>());
		}		

		List<Choice> choicesList = new ArrayList<Choice>(gameData.getChoices().keySet());
		for(Choice choice : choicesList) {
			Integer player_id = checkWinner(choice);
			if (player_id == null) {
				// maybe log
				continue;
			}
			//else
			Player player = gameData.getPlayers().get(player_id);
			List<Reward> rewards = this.gameData.getChoices().get(choice).getRewards();			
			for (Reward reward : rewards) {
				switch (reward.getTypeReward()) {	
					case Gold:
					case Blackmail:
					case Force:					
					incrementPlayerToken(player, reward.getTypeReward(), reward.getHowMuch());						
						break;
					case Support:
						player.setSupport(player.getSupport() + reward.getHowMuch());
						break;
					case Influence:
						incrementInfluence(player, reward);
						break;
					default:
						throw new RevolutionGameException("Unknown reward type");
				}								
			}
			
		}

		// for each player add minmum 5 tokens		
		for (Player player : gameData.getPlayers().values()) {
			Collection<Integer> values = player.getToken().values();
			int sum = 0;
			for (Integer value : values) {
				sum += value;
			}
			if (sum < minimumTokens) {
				incrementPlayerToken(player, RewardType.Gold, minimumTokens-sum);
			}
			player.setDecisions(null);
		}
		
		
		// check if game finished
		if (isGameFinish()) {
			gameData.setGameState(State.Finished);
			calcSupport();

		}
		
	}


	private void calcSupport() {
		int maxCubes = 0;
		int currCubes;
		Integer maxId;
		for (Entry<AreaName, AreaInfo> area : gameData.getAreas().entrySet()) {
			for (Entry<Integer, Player> player : gameData.getPlayers().entrySet()) {
				currCubes = player.getValue().getAreaPoints().get(area.getKey());
				if(currCubes > maxCubes){
					maxCubes=currCubes;
					maxId = player.getKey();
				}
				if(maxCubes > area.getValue().getMaxCubes()/2){
					player.getValue().setSupport(player.getValue().getSupport()+area.getValue().getPoints());
				}
			}
		}
	}


	public boolean isGameFinish() {
		for (AreaInfo areaInfo : gameData.getAreas().values()) {
			if(areaInfo.getCurrentCubes() < areaInfo.getMaxCubes())
				return false;
		}	
		return true;
		
	}

	private void incrementInfluence(Player player, Reward reward) {
		AreaName areaName = reward.getAreaName();
		if(areaName != null){
			AreaInfo areaInfo = gameData.getAreas().get(areaName);		
			if(areaInfo.getMaxCubes() > areaInfo.getCurrentCubes()) {
					Map<AreaName, Integer> playerAreaPoints = player.getAreaPoints();
					playerAreaPoints.put(areaName, playerAreaPoints.get(areaName)+1);
					areaInfo.setCurrentCubes(areaInfo.getCurrentCubes() + 1 );
			}
		}
	}

	private void incrementPlayerToken(Player player, RewardType rewardType, int howMuch) throws RevolutionGameException {
		TokenType tokenType = rewardType.toTokenType();
		Integer tokenAmount = player.getToken().get(tokenType);
		if (tokenAmount == null) {
			tokenAmount = 0;
		}
		player.getToken().put(tokenType, tokenAmount + howMuch);		
	}
	
	private int compareTokens(HashMap<TokenType, Integer> token1, HashMap<TokenType, Integer> token2) {
		if((token1 == null || token1.isEmpty()) && (token2.isEmpty() || token2 == null))
			return 0;
		int compare = compareNumbers(token1.get(TokenType.Force), token2.get(TokenType.Force));
		if(compare != 0)
			return compare;
		
		compare = compareNumbers(token1.get(TokenType.Blackmail), token2.get(TokenType.Blackmail));
		if(compare != 0)
			return compare;
		
		compare = compareNumbers(token1.get(TokenType.Gold), token2.get(TokenType.Gold));
		return compare;
	}
	
	private int compareNumbers(Integer num1, Integer num2) {
		int number1 = num1 != null ? num1.intValue() : 0;
		int number2 = num2 != null ? num2.intValue() : 0;
		return number1 - number2;
	}


	private Integer checkWinner(Choice choice) {
		// TODO need to create checkWinner function
		HashMap<TokenType, Integer> maxPlayerToken = null;
		Integer maxPlayerId = null;
		for (Entry<Integer, Player> playerObject : gameData.getPlayers().entrySet()) {
			HashMap<TokenType,Integer> playerTokens = playerObject.getValue().getDecisions().get(choice);
			if (playerTokens == null || playerTokens.isEmpty()) {
				continue;
			}
			if (maxPlayerId == null || compareTokens(maxPlayerToken, playerTokens) < 0) {
				maxPlayerToken = playerTokens;
				maxPlayerId = playerObject.getKey();
			}
			
		}
		
		for (Entry<Integer, Player> playerObject : gameData.getPlayers().entrySet()) {
			HashMap<TokenType,Integer> playerTokens = playerObject.getValue().getDecisions().get(choice);
			if (playerTokens == null || playerTokens.isEmpty() || playerObject.getKey() == maxPlayerId) {
				continue;
			}
			if (compareTokens(maxPlayerToken, playerTokens) == 0) {
				maxPlayerToken = null;
				maxPlayerId = null;
				break;
			}
			
		}
		return maxPlayerId;
	}

	private void notifyPlayers() {
		// TODO: notify players - wiat for niv
	}

	protected Map<Integer, Player> getPlayers() {
		return gameData.getPlayers();		
	}
	
	protected Map<Choice,ChoiceInfo> getChoices() {
		return gameData.getChoices();		
	}
	
	protected State getGameState() {
		return gameData.getGameState();		
	}
	
	protected int getRound() {
		return gameData.getRound();		
	}
}

