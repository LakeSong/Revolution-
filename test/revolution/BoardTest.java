package revolution;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoard() throws RevolutionGameException {
		Board board = new Board();
		board.joinGame(1);
		board.joinGame(2);
		
		
		Map<Choice, HashMap<TokenType, Integer>> decision1 = new HashMap<Choice, HashMap<TokenType, Integer>>();
		decision1.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Aristocrat).put(TokenType.Force, 1);
		
		decision1.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Captain).put(TokenType.Blackmail, 1);
		
		decision1.put(Choice.Mercenary, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Mercenary).put(TokenType.Gold, 3);
		
		Map<Choice, HashMap<TokenType, Integer>> decision2 = new HashMap<Choice, HashMap<TokenType, Integer>>();
		decision2.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Aristocrat).put(TokenType.Blackmail, 1);
		
		decision2.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Captain).put(TokenType.Gold, 3);
		
		decision2.put(Choice.Printer, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Printer).put(TokenType.Force, 1);
		
		//first round 
		board.PlayerSubmission(1, decision1);
		board.PlayerSubmission(2, decision2);
		

		
		Map<Choice,ChoiceInfo> choices = board.getChoices();
		System.out.println(choices.get(Choice.Aristocrat));
		
		Map<Integer, Player> players = board.getPlayers();
		Player player1 = players.get(1);
		Player player2 = players.get(2);
		

		//assertEquals(10, player2.getSupport());
		assertEquals(9, player1.getSupport());
		
		//second round 
/*		board.PlayerSubmission(1, decision1);*/
		/*board.PlayerSubmission(2, decision2);*/
		
//		Map<Integer, Player> players = board.getPlayers();
//		Player player1 = players.get(1);
		//assertEquals(player2.getSupport(), 10);
		//assertEquals(player1.getToken().get(TokenType.Gold).intValue(), 2);
				
	}

}
