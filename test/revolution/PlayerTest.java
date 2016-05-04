/**
 * 
 */
package revolution;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Shir
 *
 */
public class PlayerTest {
	
	/**
	 * Test method for {@link revolution.Player#checkAndSetDecision(java.util.Map, java.util.Map)}.
	 * @throws BadPlayerDecision 
	 */
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionNull() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = null;
		Map<Choice, HashMap<TokenType, Integer>> decisions = null;
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionEmptyDesicion() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, HashMap<TokenType, Integer>> decisions = new HashMap<Choice, HashMap<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, (HashMap<Choice, ChoiceInfo>) choices);
	}
	
	@Test	
	public void testCheckAndSetDecisionGoodDescion() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, HashMap<TokenType, Integer>> decisions = new HashMap<Choice, HashMap<TokenType, Integer>>();
		
	/*	Map<TokenType, Integer> token = new HashMap<TokenType, Integer>();
		token.put(TokenType.Gold,3);
		player.setToken(token);
		token.put(TokenType.Force, 1);
		player.setToken(token);
		token.put(TokenType.Blackmail, 1);
		player.setToken(token);
		*/
		
		
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
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Force, 1);
		
		decisions.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Captain).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.Mercenary, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Mercenary).put(TokenType.Gold, 3);
		
		player.checkAndSetDecision(decisions, choices);
	}//TODO fix erased decisions
	
	@Test	
	public void testCheckAndSetDecisionGoodDescion1() throws BadPlayerDecision {
		Player player = new Player(2);
		
		Map<TokenType, Integer> token = new HashMap<TokenType, Integer>();
		token.put(TokenType.Gold, 5);
		player.setToken(token);
		token.put(TokenType.Force, 4);
		player.setToken(token);
		token.put(TokenType.Blackmail, 1);
		player.setToken(token);
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, HashMap<TokenType, Integer>> decisions = new HashMap<Choice, HashMap<TokenType, Integer>>();
		
		
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
		capRewards.add(capReward);
		capRewards.add(capReward1);
		capRewards.add(capReward2);
		
		captainChoiceInfo.setRewards(capRewards);
		
		choices.put(Choice.Captain, captainChoiceInfo);
		
		ChoiceInfo genChoiceInfo = new ChoiceInfo();
		Restriction genRestriction = Restriction.NoForce;
		genChoiceInfo.setRestriction(genRestriction);
		List<Reward> genRewards = new ArrayList<Reward>();
		Reward genReward = new Reward(1, RewardType.Support, null);
		Reward genReward1 = new Reward(1, RewardType.Force, null);
		Reward genReward2 = new Reward(1, RewardType.Influence, AreaName.Harbor);
		genRewards.add(genReward);
		genRewards.add(genReward1);
		genRewards.add(genReward2);
		
		genChoiceInfo.setRewards(genRewards);
		
		choices.put(Choice.General, genChoiceInfo);
		
		ChoiceInfo InnChoiceInfo = new ChoiceInfo();
		Restriction InnRestriction = Restriction.NoBlackmail;
		InnChoiceInfo.setRestriction(InnRestriction);
		List<Reward> InnRewards = new ArrayList<Reward>();
		Reward InnReward = new Reward(3, RewardType.Support, null);
		Reward InnReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward InnReward2 = new Reward(1, RewardType.Influence, AreaName.Tavern);
		InnRewards.add(InnReward);
		InnRewards.add(InnReward1);
		InnRewards.add(InnReward2);
		
		InnChoiceInfo.setRewards(InnRewards);
		
		choices.put(Choice.Innkeeper, InnChoiceInfo);
		
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Gold, 3);
		
		decisions.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Captain).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.General, new HashMap<TokenType, Integer>());
		decisions.get(Choice.General).put(TokenType.Gold, 5);
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.Innkeeper, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Innkeeper).put(TokenType.Force, 2);
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Force, 2);
		
		
		
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionMoreThan6Descions() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<TokenType, Integer> token = new HashMap<TokenType, Integer>();
		token.put(TokenType.Gold,11);
		player.setToken(token);
		token.put(TokenType.Force, 5);
		player.setToken(token);
		token.put(TokenType.Blackmail, 1);
		player.setToken(token);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, HashMap<TokenType, Integer>> decisions = new HashMap<Choice, HashMap<TokenType, Integer>>();
		
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
		capRewards.add(capReward);
		capRewards.add(capReward1);
		capRewards.add(capReward2);
		
		captainChoiceInfo.setRewards(capRewards);
		
		choices.put(Choice.Captain, captainChoiceInfo);
		
		ChoiceInfo genChoiceInfo = new ChoiceInfo();
		Restriction genRestriction = Restriction.NoForce;
		genChoiceInfo.setRestriction(genRestriction);
		List<Reward> genRewards = new ArrayList<Reward>();
		Reward genReward = new Reward(1, RewardType.Support, null);
		Reward genReward1 = new Reward(1, RewardType.Force, null);
		Reward genReward2 = new Reward(1, RewardType.Influence, AreaName.Harbor);
		genRewards.add(genReward);
		genRewards.add(genReward1);
		genRewards.add(genReward2);
		
		genChoiceInfo.setRewards(genRewards);
		
		choices.put(Choice.General, genChoiceInfo);
		
		ChoiceInfo InnChoiceInfo = new ChoiceInfo();
		Restriction InnRestriction = Restriction.NoBlackmail;
		InnChoiceInfo.setRestriction(InnRestriction);
		List<Reward> InnRewards = new ArrayList<Reward>();
		Reward InnReward = new Reward(3, RewardType.Support, null);
		Reward InnReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward InnReward2 = new Reward(1, RewardType.Influence, AreaName.Tavern);
		InnRewards.add(InnReward);
		InnRewards.add(InnReward1);
		InnRewards.add(InnReward2);
		
		InnChoiceInfo.setRewards(InnRewards);
		
		choices.put(Choice.Innkeeper, InnChoiceInfo);
		
		
		ChoiceInfo magChoiceInfo = new ChoiceInfo();
		Restriction magRestriction = Restriction.NoBlackmail;
		magChoiceInfo.setRestriction(magRestriction);
		List<Reward> magRewards = new ArrayList<Reward>();
		Reward magReward = new Reward(1, RewardType.Support, null);
		Reward magReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward magReward2 = new Reward(1, RewardType.Influence, AreaName.TownHall);
		magRewards.add(magReward);
		magRewards.add(magReward1);
		magRewards.add(magReward2);
		
		magChoiceInfo.setRewards(magRewards);
		
		choices.put(Choice.Magistrate, magChoiceInfo);
		
		
		ChoiceInfo prChoiceInfo = new ChoiceInfo();
		Restriction prRestriction = Restriction.NoRestriction;
		prChoiceInfo.setRestriction(prRestriction);
		List<Reward> prRewards = new ArrayList<Reward>();
		Reward prReward = new Reward(6, RewardType.Support, null);
		Reward prReward1 = new Reward(1, RewardType.Influence, AreaName.Cathedral);
		prRewards.add(prReward);
		prRewards.add(prReward1);
		
		prChoiceInfo.setRewards(prRewards);
		
		choices.put(Choice.Priest, prChoiceInfo);
		
		
		ChoiceInfo merChoiceInfo = new ChoiceInfo();
		Restriction merRestriction = Restriction.NoRestriction;
		merChoiceInfo.setRestriction(merRestriction);
		List<Reward> merRewards = new ArrayList<Reward>();
		Reward merReward = new Reward(3, RewardType.Support, null);
		Reward merReward1 = new Reward(5, RewardType.Gold, null);
		Reward merReward2 = new Reward(1, RewardType.Influence, AreaName.Market);
		merRewards.add(merReward);
		merRewards.add(merReward1);
		merRewards.add(merReward2);
		
		merChoiceInfo.setRewards(merRewards);
		
		choices.put(Choice.Merchant, merChoiceInfo);
		
		
		ChoiceInfo printerChoiceInfo = new ChoiceInfo();
		Restriction printerRestriction = Restriction.NoRestriction;
		printerChoiceInfo.setRestriction(printerRestriction);
		List<Reward> printerRewards = new ArrayList<Reward>();
		Reward printerReward = new Reward(10, RewardType.Support, null);
		printerRewards.add(printerReward);

		printerChoiceInfo.setRewards(printerRewards);
		
		choices.put(Choice.Printer, printerChoiceInfo);
		
		
		ChoiceInfo rogChoiceInfo = new ChoiceInfo();
		Restriction rogRestriction = Restriction.Neither;
		rogChoiceInfo.setRestriction(rogRestriction);
		List<Reward> rogRewards = new ArrayList<Reward>();
		Reward rogReward = new Reward(2, RewardType.Blackmail, null);
		rogRewards.add(rogReward);

		rogChoiceInfo.setRewards(rogRewards);
		
		choices.put(Choice.Rogue, rogChoiceInfo);
		
		
		ChoiceInfo mercChoiceInfo = new ChoiceInfo();
		Restriction mercRestriction = Restriction.Neither;
		mercChoiceInfo.setRestriction(mercRestriction);
		List<Reward> mercRewards = new ArrayList<Reward>();
		Reward mercReward = new Reward(3, RewardType.Support, null);
		Reward mercReward1 = new Reward(1, RewardType.Force, null);
		mercRewards.add(mercReward);
		mercRewards.add(mercReward1);
		
		mercChoiceInfo.setRewards(mercRewards);
		
		choices.put(Choice.Mercenary, mercChoiceInfo);
		
		
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Gold, 3);
		
		decisions.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Captain).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.General, new HashMap<TokenType, Integer>());
		decisions.get(Choice.General).put(TokenType.Gold, 5);
		
		decisions.put(Choice.Innkeeper, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Innkeeper).put(TokenType.Force, 1);
		
		decisions.put(Choice.Magistrate, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Magistrate).put(TokenType.Force, 2);
		
		decisions.put(Choice.Priest, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Priest).put(TokenType.Force, 2);
		
		decisions.put(Choice.Printer, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Printer).put(TokenType.Gold, 3);	
		
		
		player.checkAndSetDecision(decisions, choices);
		
	}
	
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionMoreTokensThanYouHave() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, HashMap<TokenType, Integer>> decisions = new HashMap<Choice, HashMap<TokenType, Integer>>();
		
		
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
		capRewards.add(capReward);
		capRewards.add(capReward1);
		capRewards.add(capReward2);
		
		captainChoiceInfo.setRewards(capRewards);
		
		choices.put(Choice.Captain, captainChoiceInfo);
		
		ChoiceInfo genChoiceInfo = new ChoiceInfo();
		Restriction genRestriction = Restriction.NoForce;
		genChoiceInfo.setRestriction(genRestriction);
		List<Reward> genRewards = new ArrayList<Reward>();
		Reward genReward = new Reward(1, RewardType.Support, null);
		Reward genReward1 = new Reward(1, RewardType.Force, null);
		Reward genReward2 = new Reward(1, RewardType.Influence, AreaName.Harbor);
		genRewards.add(genReward);
		genRewards.add(genReward1);
		genRewards.add(genReward2);
		
		genChoiceInfo.setRewards(genRewards);
		
		choices.put(Choice.General, genChoiceInfo);
		
		ChoiceInfo InnChoiceInfo = new ChoiceInfo();
		Restriction InnRestriction = Restriction.NoBlackmail;
		InnChoiceInfo.setRestriction(InnRestriction);
		List<Reward> InnRewards = new ArrayList<Reward>();
		Reward InnReward = new Reward(3, RewardType.Support, null);
		Reward InnReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward InnReward2 = new Reward(1, RewardType.Influence, AreaName.Tavern);
		InnRewards.add(InnReward);
		InnRewards.add(InnReward1);
		InnRewards.add(InnReward2);
		
		InnChoiceInfo.setRewards(InnRewards);
		
		choices.put(Choice.Innkeeper, InnChoiceInfo);
		
		
		ChoiceInfo magChoiceInfo = new ChoiceInfo();
		Restriction magRestriction = Restriction.NoBlackmail;
		magChoiceInfo.setRestriction(magRestriction);
		List<Reward> magRewards = new ArrayList<Reward>();
		Reward magReward = new Reward(1, RewardType.Support, null);
		Reward magReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward magReward2 = new Reward(1, RewardType.Influence, AreaName.TownHall);
		magRewards.add(magReward);
		magRewards.add(magReward1);
		magRewards.add(magReward2);
		
		magChoiceInfo.setRewards(magRewards);
		
		choices.put(Choice.Magistrate, magChoiceInfo);
		
		
		ChoiceInfo prChoiceInfo = new ChoiceInfo();
		Restriction prRestriction = Restriction.NoRestriction;
		prChoiceInfo.setRestriction(prRestriction);
		List<Reward> prRewards = new ArrayList<Reward>();
		Reward prReward = new Reward(6, RewardType.Support, null);
		Reward prReward1 = new Reward(1, RewardType.Influence, AreaName.Cathedral);
		prRewards.add(prReward);
		prRewards.add(prReward1);
		
		prChoiceInfo.setRewards(prRewards);
		
		choices.put(Choice.Priest, prChoiceInfo);
		
		
		ChoiceInfo merChoiceInfo = new ChoiceInfo();
		Restriction merRestriction = Restriction.NoRestriction;
		merChoiceInfo.setRestriction(merRestriction);
		List<Reward> merRewards = new ArrayList<Reward>();
		Reward merReward = new Reward(3, RewardType.Support, null);
		Reward merReward1 = new Reward(5, RewardType.Gold, null);
		Reward merReward2 = new Reward(1, RewardType.Influence, AreaName.Market);
		merRewards.add(merReward);
		merRewards.add(merReward1);
		merRewards.add(merReward2);
		
		merChoiceInfo.setRewards(merRewards);
		
		choices.put(Choice.Merchant, merChoiceInfo);
		
		
		ChoiceInfo printerChoiceInfo = new ChoiceInfo();
		Restriction printerRestriction = Restriction.NoRestriction;
		printerChoiceInfo.setRestriction(printerRestriction);
		List<Reward> printerRewards = new ArrayList<Reward>();
		Reward printerReward = new Reward(10, RewardType.Support, null);
		printerRewards.add(printerReward);

		printerChoiceInfo.setRewards(printerRewards);
		
		choices.put(Choice.Printer, printerChoiceInfo);
		
		
		ChoiceInfo rogChoiceInfo = new ChoiceInfo();
		Restriction rogRestriction = Restriction.Neither;
		rogChoiceInfo.setRestriction(rogRestriction);
		List<Reward> rogRewards = new ArrayList<Reward>();
		Reward rogReward = new Reward(2, RewardType.Blackmail, null);
		rogRewards.add(rogReward);

		rogChoiceInfo.setRewards(rogRewards);
		
		choices.put(Choice.Rogue, rogChoiceInfo);
		
		
		ChoiceInfo mercChoiceInfo = new ChoiceInfo();
		Restriction mercRestriction = Restriction.Neither;
		mercChoiceInfo.setRestriction(mercRestriction);
		List<Reward> mercRewards = new ArrayList<Reward>();
		Reward mercReward = new Reward(3, RewardType.Support, null);
		Reward mercReward1 = new Reward(1, RewardType.Force, null);
		mercRewards.add(mercReward);
		mercRewards.add(mercReward1);
		
		mercChoiceInfo.setRewards(mercRewards);
		
		choices.put(Choice.Mercenary, mercChoiceInfo);
		
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Gold, 3);
		
		decisions.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Captain).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.General, new HashMap<TokenType, Integer>());
		decisions.get(Choice.General).put(TokenType.Gold, 5);
		
		decisions.put(Choice.Innkeeper, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Innkeeper).put(TokenType.Force, 1);
		
		
		
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)
	public void testCheckAndSetDecisionAgainstRestrictions() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, HashMap<TokenType, Integer>> decisions = new HashMap<Choice, HashMap<TokenType, Integer>>();
		
		Map<TokenType, Integer> token = new HashMap<TokenType, Integer>();
		token.put(TokenType.Gold,3);
		player.setToken(token);
		token.put(TokenType.Force, 2);
		player.setToken(token);
		token.put(TokenType.Blackmail, 2);
		player.setToken(token);
		
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
		capRewards.add(capReward);
		capRewards.add(capReward1);
		capRewards.add(capReward2);
		
		captainChoiceInfo.setRewards(capRewards);
		
		choices.put(Choice.Captain, captainChoiceInfo);
		
		ChoiceInfo genChoiceInfo = new ChoiceInfo();
		Restriction genRestriction = Restriction.NoForce;
		genChoiceInfo.setRestriction(genRestriction);
		List<Reward> genRewards = new ArrayList<Reward>();
		Reward genReward = new Reward(1, RewardType.Support, null);
		Reward genReward1 = new Reward(1, RewardType.Force, null);
		Reward genReward2 = new Reward(1, RewardType.Influence, AreaName.Harbor);
		genRewards.add(genReward);
		genRewards.add(genReward1);
		genRewards.add(genReward2);
		
		genChoiceInfo.setRewards(genRewards);
		
		choices.put(Choice.General, genChoiceInfo);
		
		ChoiceInfo InnChoiceInfo = new ChoiceInfo();
		Restriction InnRestriction = Restriction.NoBlackmail;
		InnChoiceInfo.setRestriction(InnRestriction);
		List<Reward> InnRewards = new ArrayList<Reward>();
		Reward InnReward = new Reward(3, RewardType.Support, null);
		Reward InnReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward InnReward2 = new Reward(1, RewardType.Influence, AreaName.Tavern);
		InnRewards.add(InnReward);
		InnRewards.add(InnReward1);
		InnRewards.add(InnReward2);
		
		InnChoiceInfo.setRewards(InnRewards);
		
		choices.put(Choice.Innkeeper, InnChoiceInfo);
		
		
		ChoiceInfo magChoiceInfo = new ChoiceInfo();
		Restriction magRestriction = Restriction.NoBlackmail;
		magChoiceInfo.setRestriction(magRestriction);
		List<Reward> magRewards = new ArrayList<Reward>();
		Reward magReward = new Reward(1, RewardType.Support, null);
		Reward magReward1 = new Reward(1, RewardType.Blackmail, null);
		Reward magReward2 = new Reward(1, RewardType.Influence, AreaName.TownHall);
		magRewards.add(magReward);
		magRewards.add(magReward1);
		magRewards.add(magReward2);
		
		magChoiceInfo.setRewards(magRewards);
		
		choices.put(Choice.Magistrate, magChoiceInfo);
		
		
		ChoiceInfo prChoiceInfo = new ChoiceInfo();
		Restriction prRestriction = Restriction.NoRestriction;
		prChoiceInfo.setRestriction(prRestriction);
		List<Reward> prRewards = new ArrayList<Reward>();
		Reward prReward = new Reward(6, RewardType.Support, null);
		Reward prReward1 = new Reward(1, RewardType.Influence, AreaName.Cathedral);
		prRewards.add(prReward);
		prRewards.add(prReward1);
		
		prChoiceInfo.setRewards(prRewards);
		
		choices.put(Choice.Priest, prChoiceInfo);
		
		
		ChoiceInfo merChoiceInfo = new ChoiceInfo();
		Restriction merRestriction = Restriction.NoRestriction;
		merChoiceInfo.setRestriction(merRestriction);
		List<Reward> merRewards = new ArrayList<Reward>();
		Reward merReward = new Reward(3, RewardType.Support, null);
		Reward merReward1 = new Reward(5, RewardType.Gold, null);
		Reward merReward2 = new Reward(1, RewardType.Influence, AreaName.Market);
		merRewards.add(merReward);
		merRewards.add(merReward1);
		merRewards.add(merReward2);
		
		merChoiceInfo.setRewards(merRewards);
		
		choices.put(Choice.Merchant, merChoiceInfo);
		
		
		ChoiceInfo printerChoiceInfo = new ChoiceInfo();
		Restriction printerRestriction = Restriction.NoRestriction;
		printerChoiceInfo.setRestriction(printerRestriction);
		List<Reward> printerRewards = new ArrayList<Reward>();
		Reward printerReward = new Reward(10, RewardType.Support, null);
		printerRewards.add(printerReward);

		printerChoiceInfo.setRewards(printerRewards);
		
		choices.put(Choice.Printer, printerChoiceInfo);
		
		
		ChoiceInfo rogChoiceInfo = new ChoiceInfo();
		Restriction rogRestriction = Restriction.Neither;
		rogChoiceInfo.setRestriction(rogRestriction);
		List<Reward> rogRewards = new ArrayList<Reward>();
		Reward rogReward = new Reward(2, RewardType.Blackmail, null);
		rogRewards.add(rogReward);

		rogChoiceInfo.setRewards(rogRewards);
		
		choices.put(Choice.Rogue, rogChoiceInfo);
		
		
		ChoiceInfo mercChoiceInfo = new ChoiceInfo();
		Restriction mercRestriction = Restriction.Neither;
		mercChoiceInfo.setRestriction(mercRestriction);
		List<Reward> mercRewards = new ArrayList<Reward>();
		Reward mercReward = new Reward(3, RewardType.Support, null);
		Reward mercReward1 = new Reward(1, RewardType.Force, null);
		mercRewards.add(mercReward);
		mercRewards.add(mercReward1);
		
		mercChoiceInfo.setRewards(mercRewards);
		
		choices.put(Choice.Mercenary, mercChoiceInfo);
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Gold, 3);
		
		decisions.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Captain).put(TokenType.Force, 1);
		
		decisions.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Aristocrat).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.Mercenary, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Mercenary).put(TokenType.Blackmail, 1);
		
		decisions.put(Choice.Mercenary, new HashMap<TokenType, Integer>());
		decisions.get(Choice.Mercenary).put(TokenType.Force, 1);
		
		player.checkAndSetDecision(decisions, choices);
	}
}
