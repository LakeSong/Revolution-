package revolution;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import sun.rmi.transport.proxy.CGIHandler;

public class Player {
	private int playerId;
	private int support;
	private Map<AreaName, Integer> areaPoints; // Map< area name, cubes>
	private Map<TokenType, Integer> token;
	private Map<Choice, HashMap<TokenType, Integer>> decisions; // choice-token-how
															// many of each
	
	public Player(int playerId) {
		this.playerId = playerId;
		support = 0;
		areaPoints = new HashMap<>();
		for(AreaName area : AreaName.values()){
			areaPoints.put(area, 0);
		}
		token = new HashMap<TokenType, Integer>();
		token.put(TokenType.Gold, 3);
		token.put(TokenType.Force, 1);
		token.put(TokenType.Blackmail, 1);
		decisions = null;
	}

	public void checkAndSetDecision(
			Map<Choice, HashMap<TokenType, Integer>> decision, Map<Choice, ChoiceInfo> choices) throws BadPlayerDecision {
		
		if (choices == null || decision == null || decision.isEmpty() || choices.isEmpty()) {
			throw new BadPlayerDecision("Empty decisions");
		}
		//TODO: check and set decision
		// validate new decision
		// should fail if a player submit more than 6 decisions
		// should fail if player didn't use all tokens
		// should fail if player use tokens he doesn't have
		// should fail if a player decision is against restrictions
		
		if(decision.size() > 6)
			throw new BadPlayerDecision("More than 6 decisions");//should fail because player made more than 6 decisions
		
		
		//counts the amount of each token that the player used
		int[] arrSum = {0,0,0};
		int[] arrToken = {0,0,0};
		for(Choice a : decision.keySet()){
			for(TokenType t : decision.get(a).keySet()){
				arrSum[t.ordinal()] += decision.get(a).get(t);//counts how many tokens players used of each type
			}
		}
		
		for (TokenType t : token.keySet()) {
			arrToken[t.ordinal()] += token.get(t); 	
		}
		
		
		int i = 0;
		for(; i<3; i++){
			if(arrToken[i] != arrSum[i])
				throw new BadPlayerDecision("Used incorrect amount of tokens where i=" + i +"arrToken[i]= " + arrToken[i]+ "arrSum[i]= "+ arrSum[i]);
			//should fail if not same amount as player had at the beginning of the round
		}
		
		
		Restriction restriction;//need to check how to get choice info with choice name
		ChoiceInfo ch = new ChoiceInfo();
		for(Choice c : decision.keySet()){
			ch = choices.get(c);
			restriction = ch.getRestriction();
			//boolean flag = isValid(c, restriction);
			if((restriction.toString().equals("NoBlackmail") && decision.get(c).containsKey(TokenType.Blackmail)) ||
				(restriction.toString().equals("NoForce") && decision.get(c).containsKey(TokenType.Force)) ||
				(restriction.toString().equals("Neither") && 
				(decision.get(c).containsKey(TokenType.Force) || decision.get(c).containsKey(TokenType.Blackmail)))){
				throw new BadPlayerDecision("One or more decisions are against restrictions");//;//should fail because decision is against restrictions
			}
			//if(!flag)
				//throw new BadPlayerDecision("One or more decisions are against restrictions");//throw new BadPlayerDecision("One or more decisions are against restrictions");//should fail because decision is against restrictions
		}
		
		this.decisions = decision;
		// set decision
	}

	private boolean isValid(Choice c, Restriction restriction) {
		
		int res = restriction.ordinal();
		switch (res) {
		case 0:{
			return true;
			}
		case 1:{
			if(decisions.get(c).containsKey(TokenType.Blackmail))
				return false;
			break;}
		case 2:{
			if(decisions.get(c).containsKey(TokenType.Force))
				return false;
			break;}		
		case 3:{
			if(decisions.get(c).containsKey(TokenType.Blackmail) || decisions.get(c).containsKey(TokenType.Force))
				return false;
			break;}
		default:
			break;
		}
		
		return false;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public Map<AreaName, Integer> getAreaPoints() {
		return areaPoints;
	}

	public void setAreaPoints(Map<AreaName, Integer> areaPoints) {
		this.areaPoints = areaPoints;
	}

	public Map<TokenType, Integer> getToken() {
		return token;
	}

	public void setToken(Map<TokenType, Integer> token) {
		this.token = token;
	}

	public Map<Choice, HashMap<TokenType, Integer>> getDecisions() {
		return decisions;
	}

	public void setDecisions(Map<Choice, HashMap<TokenType, Integer>> decisions) {
		this.decisions = decisions;
	}

	public int getSupport() {
		return support;
	}

	public void setSupport(int support) {
		this.support = support;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((areaPoints == null) ? 0 : areaPoints.hashCode());
		result = prime * result
				+ ((decisions == null) ? 0 : decisions.hashCode());
		result = prime * result + playerId;
		result = prime * result + support;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if (areaPoints == null) {
			if (other.areaPoints != null)
				return false;
		} else if (!areaPoints.equals(other.areaPoints))
			return false;
		if (decisions == null) {
			if (other.decisions != null)
				return false;
		} else if (!decisions.equals(other.decisions))
			return false;
		if (playerId != other.playerId)
			return false;
		if (support != other.support)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", points=" + support
				+ ", area_points=" + areaPoints + ", token=" + token
				+ ", decisions=" + decisions + "]";
	}

}
