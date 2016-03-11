package revolution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private static final int minimumTokens = 5;

	private Map<AreaName, AreaInfo> areas;
	
	private Map<Choice,ChoiceInfo> choices;

	private State gameState;
	
	private Map<Integer, Player> players; // Map<playerId, Player>

	
	public Board() {
		areas = new HashMap<AreaName, AreaInfo>();
		for (AreaName areaName : AreaName.values()) {
			areas.put(areaName, new AreaInfo());
		}
		choices = new HashMap<Choice, ChoiceInfo>();
		initChoices();		
		
		
		gameState = State.Inprogress;
		players = new HashMap<Integer, Player>();
	}
	
	private void initChoices() {
		// TODO: define one nby one
		
		
	}
	
	public void joinGame(int playerId) {
		players.put(playerId,  new Player(playerId));
	}

	public void PlayerSubmission(int playerId, Map<Choice, Map<TokenType, Integer>> decision) throws RevolutionGameException {

		if(gameState == State.Finished) {
			throw new RevolutionGameException("Game already finished");
		}
		
		Player player = this.players.get(playerId);
		
		if (player == null)
			throw new RevolutionGameException("Player not found");

		player.checkAndSetDecision(decision);
		
		if (lastPlayer()) {
			turnFinished();
			notifyPlayers();
		}
	}

	private boolean lastPlayer() {
		for (Player player : players.values()) {
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
	private void turnFinished() throws RevolutionGameException {
		 // new token map for each player		
		for(Player player : players.values()) {
			player.setToken(new HashMap<TokenType, Integer>());
		}		

		List<Choice> choicesList = new ArrayList<Choice>(choices.keySet());
		for(Choice choice : choicesList) {
			Integer player_id = checkWinner(choice);
			if (player_id == null) {
				// maybe log
				continue;
			}
			//else
			Player player = players.get(player_id);
			List<Reward> rewards = this.choices.get(choice).getRewards();			
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
		for (Player player : players.values()) {
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
			gameState = State.Finished;
		}
		
	}

	private boolean isGameFinish() {
		for (AreaInfo areaInfo : areas.values()) {
			if(areaInfo.getCurrentCubes() < areaInfo.getMaxCubes())
				return false;
		}	
		return true;
		
	}

	private void incrementInfluence(Player player, Reward reward) {
		AreaName areaName = reward.getAreaName();
		AreaInfo areaInfo = areas.get(areaName);		
		if(areaInfo.getMaxCubes() > areaInfo.getCurrentCubes()) {
				Map<AreaName, Integer> playerAreaPoints = player.getAreaPoints();
				playerAreaPoints.put(areaName, playerAreaPoints.get(areaName)+1);
				areaInfo.setCurrentCubes(areaInfo.getCurrentCubes() + 1 );
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

	private Integer checkWinner(Choice choice) {
		// TODO Auto-generated method stub
		return null;
	}

	private void notifyPlayers() {
		// TODO: notify players - wiat for niv
	}

}

