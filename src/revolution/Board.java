package revolution;

import java.util.Map;

public class Board {
	private static Map<AreaName, AreaInfo> areas;

	private static  Map<Choice,ChoiceInfo> choices;

	private State gameState;
	
	private Map<Integer, Player> players; // Map<playerId, Player>

	public void PlayerSubmission(int playerId, Map<Choice, Map<TokenType, Integer>> decision) {
		// save the players state 
		// if its the last player
		//      update the board 
		//      notify all players
		//
		// should fail if a player submit more than 6 choices
		// should fail if player didn't use all tokens
		// should fail if player use tokens he doesntl have
		// should fail if a player decision is againts resctrictions 
		// should fail if palyer sumbited in the second time ( before the turn is over)
		// should fail if the game is over
	}

	private void turnFinished() {
		// new token map for each plaer
		// for each area sum total cubes
		// for each choice
		//     decide which player won
		//     sum player reward to player token map
		//     sum player reward points 
		//     if areas sum total < max:
		//           sum player areas cubes ++
		//           area sum total cubes ++ 
		// for each player
		//    token map should be at least 5 tokens if not add gold
		// 	check if the game is finished
	}

	private void notifyPlayers() {

	}

}
