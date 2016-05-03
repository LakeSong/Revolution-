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
		ChoiceInfo aristocratChoiceInfo = new ChoiceInfo();
		Restriction restriction = Restriction.NoRestriction;
		aristocratChoiceInfo.setRestriction(restriction);
		List<Reward> rewards = new ArrayList<Reward>();
		Reward reward = new Reward(5, RewardType.Support, null);
		Reward reward1 = new Reward(3, RewardType.Gold, null);
		Reward reward2 = new Reward(1, RewardType.Influence , AreaName.Plantation);
		rewards.add(reward);
		rewards.add(reward1);
		rewards.add(reward2);
		
		aristocratChoiceInfo.setRewards(rewards);
		
		choices.put(Choice.Aristocrat, aristocratChoiceInfo);
		
		ChoiceInfo captainChoiceInfo = new ChoiceInfo();
		Restriction capRestriction = Restriction.NoForce;
		captainChoiceInfo.setRestriction(capRestriction);
		List<Reward> capRewards = new ArrayList<Reward>();
		Reward capReward = new Reward(1, RewardType.Support, null);
		Reward capReward1 = new Reward(1, RewardType.Force, null);
		Reward capReward2 = new Reward(1, RewardType.Influence, AreaName.Fortress);
		rewards.add(capReward);
		rewards.add(capReward1);
		rewards.add(capReward2);
		
		captainChoiceInfo.setRewards(capRewards);
		
		choices.put(Choice.Captain, captainChoiceInfo);
		
		ChoiceInfo genChoiceInfo = new ChoiceInfo();
		Restriction genRestriction = Restriction.NoForce;
		genChoiceInfo.setRestriction(genRestriction);
		List<Reward> genRewards = new ArrayList<Reward>();
		Reward genReward = new Reward(1, RewardType.Support, null);
		Reward genReward1 = new Reward(1, RewardType.Force, null);
		Reward genReward2 = new Reward(1, RewardType.Influence, AreaName.Harbor);
		rewards.add(genReward);
		rewards.add(genReward1);
		rewards.add(genReward2);
		
		genChoiceInfo.setRewards(genRewards);
		
		choices.put(Choice.General, genChoiceInfo);
		
		ChoiceInfo InnChoiceInfo = new ChoiceInfo();
		Restriction InnRestriction = Restriction.NoBlackmail;
		InnChoiceInfo.setRestriction(InnRestriction);
		List<Reward> InnRewards = new ArrayList<Reward>();
		Reward InnReward = new Reward(3, RewardType.Support, null);
		Reward InnReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward InnReward2 = new Reward(1, RewardType.Influence, AreaName.Tavern);
		rewards.add(InnReward);
		rewards.add(InnReward1);
		rewards.add(InnReward2);
		
		InnChoiceInfo.setRewards(InnRewards);
		
		choices.put(Choice.Innkeeper, InnChoiceInfo);
		
		
		ChoiceInfo magChoiceInfo = new ChoiceInfo();
		Restriction magRestriction = Restriction.NoBlackmail;
		magChoiceInfo.setRestriction(magRestriction);
		List<Reward> magRewards = new ArrayList<Reward>();
		Reward magReward = new Reward(1, RewardType.Support, null);
		Reward magReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward magReward2 = new Reward(1, RewardType.Influence, AreaName.TownHall);
		rewards.add(magReward);
		rewards.add(magReward1);
		rewards.add(magReward2);
		
		magChoiceInfo.setRewards(magRewards);
		
		choices.put(Choice.Magistrate, magChoiceInfo);
		
		
		ChoiceInfo prChoiceInfo = new ChoiceInfo();
		Restriction prRestriction = Restriction.NoRestriction;
		prChoiceInfo.setRestriction(prRestriction);
		List<Reward> prRewards = new ArrayList<Reward>();
		Reward prReward = new Reward(6, RewardType.Support, null);
		Reward prReward1 = new Reward(1, RewardType.Influence, AreaName.Cathedral);
		rewards.add(prReward);
		rewards.add(prReward1);
		
		prChoiceInfo.setRewards(prRewards);
		
		choices.put(Choice.Priest, prChoiceInfo);
		
		
		ChoiceInfo merChoiceInfo = new ChoiceInfo();
		Restriction merRestriction = Restriction.NoRestriction;
		merChoiceInfo.setRestriction(merRestriction);
		List<Reward> merRewards = new ArrayList<Reward>();
		Reward merReward = new Reward(3, RewardType.Support, null);
		Reward merReward1 = new Reward(5, RewardType.Gold, null);
		Reward merReward2 = new Reward(1, RewardType.Influence, AreaName.Market);
		rewards.add(merReward);
		rewards.add(merReward1);
		rewards.add(merReward2);
		
		merChoiceInfo.setRewards(merRewards);
		
		choices.put(Choice.Merchant, merChoiceInfo);
		
		
		ChoiceInfo printerChoiceInfo = new ChoiceInfo();
		Restriction printerRestriction = Restriction.NoRestriction;
		printerChoiceInfo.setRestriction(printerRestriction);
		List<Reward> printerRewards = new ArrayList<Reward>();
		Reward printerReward = new Reward(10, RewardType.Support, null);
		rewards.add(printerReward);

		printerChoiceInfo.setRewards(printerRewards);
		
		choices.put(Choice.Printer, printerChoiceInfo);
		
		
		ChoiceInfo rogChoiceInfo = new ChoiceInfo();
		Restriction rogRestriction = Restriction.Neither;
		rogChoiceInfo.setRestriction(rogRestriction);
		List<Reward> rogRewards = new ArrayList<Reward>();
		Reward rogReward = new Reward(2, RewardType.Blackmail, null);
		rewards.add(rogReward);

		rogChoiceInfo.setRewards(rogRewards);
		
		choices.put(Choice.Rogue, rogChoiceInfo);
		
		
		ChoiceInfo mercChoiceInfo = new ChoiceInfo();
		Restriction mercRestriction = Restriction.Neither;
		mercChoiceInfo.setRestriction(mercRestriction);
		List<Reward> mercRewards = new ArrayList<Reward>();
		Reward mercReward = new Reward(3, RewardType.Support, null);
		Reward mercReward1 = new Reward(1, RewardType.Force, null);
		rewards.add(mercReward);
		rewards.add(mercReward1);
		
		mercChoiceInfo.setRewards(mercRewards);
		
		choices.put(Choice.Mercenary, mercChoiceInfo);
		
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

		player.checkAndSetDecision(decision, choices);
		
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

