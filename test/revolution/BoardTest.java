package revolution;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {

	@Test
	public void testBoard() {
		Board board = new Board();
		board.joinGame(1);
		board.joinGame(2);
		//first round 
		board.PlayerSubmission(1, decision1);
		board.PlayerSubmission(2, decision2);
		
		Map<Integer, Player> players = board.getPlayers();
		Player player1 = players.get(1);
		assertEquals(player1.getSupport(), 3);
		assertEquals(player1.getToken().get(TokenType.Gold).intValue(), 2);
		
		//second round 
		board.PlayerSubmission(1, decision1);
		board.PlayerSubmission(2, decision2);
		
//		Map<Integer, Player> players = board.getPlayers();
//		Player player1 = players.get(1);
		assertEquals(player1.getSupport(), 3);
		assertEquals(player1.getToken().get(TokenType.Gold).intValue(), 2);
				
		
		
		fail("Not yet implemented");
	}

}
