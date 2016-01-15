package revolution;

import java.util.Map;

public class Player {
	private int playerId; // ?
	private int points;
	private Map<AreaName, Integer> area_points; // Map< area name, cubes>
	private Map<TokenType, Integer> token;
	private Map<Choice, Map<TokenType, Integer>> decisions; // choice-token-how
															// many of eacg

	public void checkAndSetDecision(
			Map<Choice, Map<TokenType, Integer>> decisions) {
		// validate new decision
		// should fail if a player submit more than 6 decisions
		// should fail if player didn't use all tokens
		// should fail if player use tokens he doesntl have
		// should fail if a player decision is againts resctrictions

		// set decision
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Map<AreaName, Integer> getArea_points() {
		return area_points;
	}

	public void setArea_points(Map<AreaName, Integer> area_points) {
		this.area_points = area_points;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((area_points == null) ? 0 : area_points.hashCode());
		result = prime * result
				+ ((decisions == null) ? 0 : decisions.hashCode());
		result = prime * result + playerId;
		result = prime * result + points;
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
		if (area_points == null) {
			if (other.area_points != null)
				return false;
		} else if (!area_points.equals(other.area_points))
			return false;
		if (decisions == null) {
			if (other.decisions != null)
				return false;
		} else if (!decisions.equals(other.decisions))
			return false;
		if (playerId != other.playerId)
			return false;
		if (points != other.points)
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
		return "Player [playerId=" + playerId + ", points=" + points
				+ ", area_points=" + area_points + ", token=" + token
				+ ", decisions=" + decisions + "]";
	}

}
