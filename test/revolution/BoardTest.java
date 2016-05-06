package revolution;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import revolution.entities.*;
import revolution.exceptions.*;
import revolution.enums.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class BoardTest {

	@Test
	public void testBoard() throws RevolutionGameException {
		Board board = new Board();
		board.joinGame(1);
		board.joinGame(2);
		board.joinGame(3);
		
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
		
		Map<Choice, HashMap<TokenType, Integer>> decision3 = new HashMap<Choice, HashMap<TokenType, Integer>>();
		decision3.put(Choice.Printer, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Printer).put(TokenType.Force, 1);
		
		decision3.put(Choice.Merchant, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Merchant).put(TokenType.Blackmail, 1);
		
		decision3.put(Choice.Mercenary, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Mercenary).put(TokenType.Gold, 1);
		
		decision3.put(Choice.Innkeeper, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Innkeeper).put(TokenType.Gold, 2);
		
		//first round 
		board.PlayerSubmission(1, decision1);
		board.PlayerSubmission(2, decision2);
		board.PlayerSubmission(3, decision3);

		
		Map<Integer, Player> players = board.getPlayers();
		Player player1 = players.get(1);
		Player player2 = players.get(2);
		Player player3=players.get(3);

		System.out.println(player1);
		System.out.println(player2);
		System.out.println(player3);
		
		assertEquals(0, player2.getSupport());
		assertEquals(9, player1.getSupport());
		
		assertEquals(6, player3.getSupport());
		
		//second round 
/*		board.PlayerSubmission(1, decision1);*/
		/*board.PlayerSubmission(2, decision2);*/
		
	
		
//		Map<Integer, Player> players = board.getPlayers();
//		Player player1 = players.get(1);
		//assertEquals(player2.getSupport(), 10);
		//assertEquals(player1.getToken().get(TokenType.Gold).intValue(), 2);
		decision1.clear();
		decision2.clear();
		decision3.clear();
		decision1.put(Choice.Captain, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Captain).put(TokenType.Gold, 3);
		
		decision1.put(Choice.Printer, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Printer).put(TokenType.Force, 1);
		
		decision1.put(Choice.Merchant, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Merchant).put(TokenType.Force, 1);
		
		decision2.put(Choice.Priest, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Priest).put(TokenType.Gold, 3);
		
		decision2.put(Choice.Rogue, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Rogue).put(TokenType.Gold, 2);
		
		decision3.put(Choice.Rogue, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Rogue).put(TokenType.Gold, 3);
		
		decision3.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Aristocrat).put(TokenType.Gold, 2);
		
		decision3.put(Choice.General, new HashMap<TokenType, Integer>());
		decision3.get(Choice.General).put(TokenType.Blackmail, 1);
		
		board.PlayerSubmission(1, decision1);
		board.PlayerSubmission(2, decision2);
		board.PlayerSubmission(3, decision3);

		
		players = board.getPlayers();
		player1 = players.get(1);
		player2 = players.get(2);
		player3=players.get(3);

		System.out.println(player1);
		System.out.println(player2);
		System.out.println(player3);
		
		
		
		decision1.clear();
		decision2.clear();
		decision3.clear();
		decision1.put(Choice.Magistrate, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Magistrate).put(TokenType.Gold, 5);
		
		decision1.get(Choice.Magistrate).put(TokenType.Force, 1);
		
		
		decision2.put(Choice.Priest, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Priest).put(TokenType.Gold, 3);
		
		decision2.put(Choice.Merchant, new HashMap<TokenType, Integer>());
		decision2.get(Choice.Merchant).put(TokenType.Gold, 2);
		
		decision3.put(Choice.Rogue, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Rogue).put(TokenType.Gold, 3);
		
		decision3.put(Choice.Magistrate, new HashMap<TokenType, Integer>());
		decision3.get(Choice.Magistrate).put(TokenType.Force, 1);
		
		decision3.put(Choice.General, new HashMap<TokenType, Integer>());
		decision3.get(Choice.General).put(TokenType.Blackmail, 2);
		
		board.PlayerSubmission(1, decision1);
		board.PlayerSubmission(2, decision2);
		board.PlayerSubmission(3, decision3);

		
		players = board.getPlayers();
		player1 = players.get(1);
		player2 = players.get(2);
		player3=players.get(3);

		System.out.println(player1);
		System.out.println(player2);
		System.out.println(player3);
	}
	
	@Test
	public void csvTest() throws RevolutionGameException {
		String s = "1,1,3";
		String[] sArr = s.split("[\\s,]+");
		Integer[] toInt = new Integer[3];
		System.out.println(Arrays.toString(sArr));
		if(sArr.length != 3){
			throw new RevolutionGameException("Entered more than 3 decisions");
		}
		//should be size 3
		for(int i=0; i<3; i++){
			toInt[i] = Integer.parseInt(sArr[i]);
		}
		Map<Choice, HashMap<TokenType, Integer>> decision1 = new HashMap<Choice, HashMap<TokenType, Integer>>();
		decision1.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Aristocrat).put(TokenType.Force, toInt[0]);
		decision1.get(Choice.Aristocrat).put(TokenType.Blackmail, toInt[1]);
		decision1.get(Choice.Aristocrat).put(TokenType.Gold, toInt[2]);
		
		System.out.println(decision1);
		
		//Integer[]
		//into desction
		
		
		
		
		
		
		
		//decision1 => String Json
		
		//string json => desicion2
		
		// check decision1.equals decision2
				
		
	}

	
	@Test
	public void jsonTest() throws RevolutionGameException {
		
		
		Map<Choice, HashMap<TokenType, Integer>> decision1 = new HashMap<Choice, HashMap<TokenType, Integer>>();
		decision1.put(Choice.Aristocrat, new HashMap<TokenType, Integer>());
		decision1.get(Choice.Aristocrat).put(TokenType.Force, 4);
		decision1.get(Choice.Aristocrat).put(TokenType.Blackmail, 3);
		decision1.get(Choice.Aristocrat).put(TokenType.Gold, 13434);

		
		//decision1 => String Json
		Gson gson = new Gson();
		String json = gson.toJson(decision1);
		System.out.println(json);
		
		//string json => desicion2
		Type type = new TypeToken<Map<Choice, HashMap<TokenType, Integer>>>() {}.getType();
		Map<Choice, HashMap<TokenType, Integer>> decision2 = gson.fromJson(json, type);
		
		
		// check decision1.equals decision2
		assertEquals(decision1, decision2);
				
		
	}
}
