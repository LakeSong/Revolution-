/**
 * 
 */
package revolution;

import static org.junit.Assert.*;

import java.util.HashMap;
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
		Map<Choice, Map<TokenType, Integer>> decisions = null;
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionEmptyDesicion() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, Map<TokenType, Integer>> decisions = new HashMap<Choice, Map<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test	
	public void testCheckAndSetDecisionGoodDescion() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, Map<TokenType, Integer>> decisions = new HashMap<Choice, Map<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, choices);
		
	}
	
	@Test	
	public void testCheckAndSetDecisionGoodDescion1() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, Map<TokenType, Integer>> decisions = new HashMap<Choice, Map<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionMoreThan6Descions() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, Map<TokenType, Integer>> decisions = new HashMap<Choice, Map<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)	
	public void testCheckAndSetDecisionMoreTokensThanYouHave() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, Map<TokenType, Integer>> decisions = new HashMap<Choice, Map<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, choices);
	}
	
	@Test(expected=BadPlayerDecision.class)
	public void testCheckAndSetDecisionAgainstRestrections() throws BadPlayerDecision {
		Player player = new Player(1);
		
		Map<Choice, ChoiceInfo> choices = new HashMap<Choice, ChoiceInfo>();
		Map<Choice, Map<TokenType, Integer>> decisions = new HashMap<Choice, Map<TokenType, Integer>>();
		player.checkAndSetDecision(decisions, choices);
	}
}
