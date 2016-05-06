package revolution.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import revolution.Board;
import revolution.entities.GameData;
import revolution.enums.Choice;
import revolution.enums.TokenType;
import revolution.exceptions.RevolutionGameException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class RevolutionServer extends WebSocketServer{
	private Board board;
	private Map<WebSocket, Integer> connectionPlayerMap;
	private int playerCounter = 0;
	
	
	public RevolutionServer( int port ) throws UnknownHostException {
		super( new InetSocketAddress( port ) );
		board = new Board();
		connectionPlayerMap = new HashMap<>();
	}

	public RevolutionServer( InetSocketAddress address ) {
		super( address );
		board = new Board();
		connectionPlayerMap = new HashMap<>();
	}
	
	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		int playerId = playerCounter++;
		connectionPlayerMap.put(conn, playerId);
		board.joinGame(playerId);
		this.sendToAll( "new connection: " + handshake.getResourceDescriptor() + " player id: "+ playerId);
		System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote ) {
		this.sendToAll(conn + " has left the room!");
		System.out.println(conn + " has left the room!" );
	}

	@Override
	public void onMessage( WebSocket conn, String message ) {
		Map<Choice, HashMap<TokenType, Integer>> decision = null;		
		try {
			Gson gson = new Gson();
			Type type = new TypeToken<Map<Choice, HashMap<TokenType, Integer>>>() {}.getType();
			decision = gson.fromJson(message, type);
		} catch (Exception e) {
			conn.send("Problem with input. not a valid json.");
			return;
		}
		
		Integer playerId = connectionPlayerMap.get(conn);
		GameData gameData = null;
		try {
			gameData = board.PlayerSubmission(playerId, decision);
			this.sendToAll("Player " + playerId + " Submit received, " 
						 	+ board.submittedPlayers() + " submitted already");
		} catch (RevolutionGameException e) {
			conn.send("Problem with input. " + e.getMessage());
			return;
		}
		
		if (gameData != null) {
			Gson gson = new Gson();
			this.sendToAll(gson.toJson(gameData));
		}
	}

	public void onFragment( WebSocket conn, Framedata fragment ) {
//		System.out.println( "received fragment: " + fragment );
	}

	public static void main( String[] args ) throws InterruptedException , IOException {
		WebSocketImpl.DEBUG = true;
		int port = 8887; // 843 flash policy port
		try {
			port = Integer.parseInt( args[ 0 ] );
		} catch ( Exception ex ) {
		}
		RevolutionServer s = new RevolutionServer( port );
		s.start();
		System.out.println( "ChatServer started on port: " + s.getPort() );

		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
		while ( true ) {
			String in = sysin.readLine();
			s.sendToAll( in );
			if( in.equals( "exit" ) ) {
				s.stop();
				break;
			} else if( in.equals( "restart" ) ) {
				s.stop();
				s.start();
				break;
			}
		}
	}
	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if( conn != null ) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	/**
	 * Sends <var>text</var> to all currently connected WebSocket clients.
	 * 
	 * @param text
	 *            The String to send across the network.
	 * @throws InterruptedException
	 *             When socket related I/O errors occur.
	 */
	public void sendToAll( String text ) {
		Collection<WebSocket> con = connections();
		synchronized ( con ) {
			for( WebSocket c : con ) {
				c.send( text );
			}
		}
	}

}
