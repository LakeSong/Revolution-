package revolution;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Player {
	private int playerId;
	private int support;
	private Map<AreaName, Integer> areaPoints; // Map< area name, cubes>
	private Map<TokenType, Integer> token;
	private Map<Choice, Map<TokenType, Integer>> decisions; // choice-token-how
															// many of each
	
	public Player(int playerId) {
		this.playerId = playerId;
		support = 0;
		areaPoints = new HashMap<>();
		token = new HashMap<>();
		token.put(TokenType.Gold, 3);
		token.put(TokenType.Force, 1);
		token.put(TokenType.Blackmail, 1);
		decisions = null;
	}

	public void checkAndSetDecision(
			Map<Choice, Map<TokenType, Integer>> decisions, Map<Choice, ChoiceInfo> choices) {
		//TODO: check and set decision
		// validate new decision
		// should fail if a player submit more than 6 decisions
		// should fail if player didn't use all tokens
		// should fail if player use tokens he doesn't have
		// should fail if a player decision is against restrictions
		
		if(decisions.size() > 6)
			;//should fail because player made more than 6 decisions
		
		
		//counts the amount of each token that the player used
		int[] arrSum = {0,0,0};
		for(Choice a : decisions.keySet()){
			for(TokenType t : decisions.get(a).keySet()){
				arrSum[t.ordinal()] += decisions.get(a).get(t);//counts how many tokens players used of each type
			}
		}
		
		int i = 0;
		for(Integer tk : token.values()){
			if(tk != arrSum[i])
				;//should fail if not same amount as player had at the beginning of the round
		}
		
		
		Restriction restriction;//need to check how to get choice info with choice name
		ChoiceInfo ch = new ChoiceInfo();
		for(Choice c : decisions.keySet()){
			ch = choices.get(c);
			restriction = ch.getRestriction();
			boolean flag = isValid(c, restriction);
			/*if((restriction.toString().equals("NoBlackmail") && decisions.get(c).containsKey(TokenType.Blackmail)) ||
				(restriction.toString().equals("NoForce") && decisions.get(c).containsKey(TokenType.Force)) ||
				(restriction.toString().equals("Neither") && 
				(decisions.get(c).containsKey(TokenType.Force) || decisions.get(c).containsKey(TokenType.Blackmail)))){
					;//should fail because decision is against restrictions
			}*/
			if(!flag)
				;//should fail because decision is against restrictions
		}
		
		this.decisions = decisions;
		// set decision
	}

	private boolean isValid(Choice c, Restriction restriction) {
		// TODO Auto-generated method stub
		
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

	public Map<Choice, Map<TokenType, Integer>> getDecisions() {
		return decisions;
	}

	public void setDecisions(Map<Choice, Map<TokenType, Integer>> decisions) {
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
