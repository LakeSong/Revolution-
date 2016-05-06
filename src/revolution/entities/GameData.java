package revolution.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import revolution.Player;
import revolution.enums.AreaName;
import revolution.enums.Choice;
import revolution.enums.Restriction;
import revolution.enums.RewardType;
import revolution.enums.State;

/**
 * @author nivm
 *
 */
public class GameData {

	private int round;
	
	private State gameState;
	
	private Map<AreaName, AreaInfo> areas;
	
	private Map<Choice,ChoiceInfo> choices;
	
	private Map<Integer, Player> players; // Map<playerId, Player>
	
	public GameData() {
		setAreas(new HashMap<AreaName, AreaInfo>());
		for (AreaName areaName : AreaName.values()) {
			getAreas().put(areaName, new AreaInfo());
		}
		setChoices(initChoices());
				
		setRound(1);
		setGameState(State.Inprogress);
		setPlayers(new HashMap<Integer, Player>());
	}
	
	private Map<Choice, ChoiceInfo> initChoices() {
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		
		choices.put(Choice.Aristocrat, new ChoiceInfo()
			.addReward(new Reward(5, RewardType.Support))
			.addReward(new Reward(3, RewardType.Gold))
			.addReward(new Reward(1, RewardType.Influence , AreaName.Plantation)));
		
		choices.put(Choice.Captain,  new ChoiceInfo(Restriction.NoForce)
				.addReward(new Reward(1, RewardType.Force))
				.addReward(new Reward(1, RewardType.Support))
				.addReward(new Reward(1, RewardType.Influence, AreaName.Fortress)));		

		choices.put(Choice.General, new ChoiceInfo(Restriction.NoForce)
		.addReward(new Reward(1, RewardType.Support))
		.addReward(new Reward(1, RewardType.Force))
		.addReward(new Reward(1, RewardType.Influence, AreaName.Harbor)));
		
		choices.put(Choice.Innkeeper, new ChoiceInfo(Restriction.NoBlackmail)
		.addReward(new Reward(3, RewardType.Support))
		.addReward(new Reward(1, RewardType.Blackmail))
		.addReward(new Reward(1, RewardType.Influence, AreaName.Tavern)));
		
		choices.put(Choice.Magistrate, new ChoiceInfo(Restriction.NoBlackmail)
		.addReward(new Reward(1, RewardType.Support))
		.addReward(new Reward(1, RewardType.Blackmail))
		.addReward(new Reward(1, RewardType.Influence, AreaName.TownHall)));
		
		choices.put(Choice.Priest, new ChoiceInfo(Restriction.NoRestriction)
		.addReward(new Reward(6, RewardType.Support))
		.addReward(new Reward(1, RewardType.Influence, AreaName.Cathedral)));
		
		choices.put(Choice.Merchant, new ChoiceInfo(Restriction.NoRestriction)
		.addReward(new Reward(3, RewardType.Support))
		.addReward(new Reward(5, RewardType.Gold))
		.addReward(new Reward(1, RewardType.Influence, AreaName.Market)));
		
		choices.put(Choice.Printer, new ChoiceInfo(Restriction.NoRestriction)
		.addReward(new Reward(10, RewardType.Support)));
		
		choices.put(Choice.Rogue, new ChoiceInfo(Restriction.Neither)
		.addReward(new Reward(2, RewardType.Blackmail)));
		
		choices.put(Choice.Mercenary, new ChoiceInfo(Restriction.Neither)
		.addReward(new Reward(3, RewardType.Support))
		.addReward(new Reward(1, RewardType.Force)));
		
		return choices;
	}

	public Map<Integer, Player> getPlayers() {
		return players;
	}

	public void setPlayers(Map<Integer, Player> players) {
		this.players = players;
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public Map<Choice,ChoiceInfo> getChoices() {
		return choices;
	}

	public void setChoices(Map<Choice,ChoiceInfo> choices) {
		this.choices = choices;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Map<AreaName, AreaInfo> getAreas() {
		return areas;
	}

	public void setAreas(Map<AreaName, AreaInfo> areas) {
		this.areas = areas;
	}

} 