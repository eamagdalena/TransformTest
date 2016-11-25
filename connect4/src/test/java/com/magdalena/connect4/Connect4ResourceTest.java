package com.magdalena.connect4;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.magdalena.connect4.messages.CreateGameResult;
import com.magdalena.connect4.messages.DropDiskResult;
import com.magdalena.connect4.messages.JoinGameResult;
import com.magdalena.connect4.model.Color;
import com.magdalena.connect4.model.Game;
import com.magdalena.connect4.model.Game.State;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class Connect4ResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testCreateGame() {
        CreateGameResult createGameResponse = target.path("/game/create/RED").request().get(CreateGameResult.class);

        assertTrue(createGameResponse.getGameId() > 0);
        assertTrue(createGameResponse.getPlayerId() > 0);
        
        assertEquals(pollGame(createGameResponse.getGameId()).getState() , State.WAITING_PLAYER_2);
        
    }
    
    @Test
    public void testJoinGame() {
        CreateGameResult createGameResponse = target.path("/game/create/RED").request().get(CreateGameResult.class);
       
        JoinGameResult joinGameResponse = target.path("/game/" + createGameResponse.getGameId() + "/join").request().get(JoinGameResult.class);
        
        assertNotSame(createGameResponse.getPlayerId(), joinGameResponse.getPlayerId());
        
        assertTrue(joinGameResponse.isConnectionAccepted());
        assertTrue(joinGameResponse.getPlayerId() > 0);
        assertEquals(joinGameResponse.getColor() , Color.YELLOW);
        
        assertEquals(pollGame(createGameResponse.getGameId()).getState() , State.PLAYER_1_TURN);
    }
    
    private Game pollGame(long gameId){
    	return target.path("/game/" + gameId + "/status").request().get(Game.class);
    }
    
    private DropDiskResult dropDisk(long playerId, int column) {
    	return DropDiskResult.valueOf(target.path("/game/player/"+playerId+"/dropOnColumn/" + column).request().get(String.class));
    }
    
    @Test
    public void testSimpleGame() {
        CreateGameResult player1 = target.path("/game/create/RED").request().get(CreateGameResult.class);
        JoinGameResult player2 = target.path("/game/" + player1.getGameId() + "/join").request().get(JoinGameResult.class);
        
        assertEquals(dropDisk(player1.getPlayerId(), 0),  DropDiskResult.ACCEPTED);
        assertEquals(pollGame(player1.getGameId()).getBoard()[0], Color.RED);
                
        assertEquals(dropDisk(player2.getPlayerId(), 1) , DropDiskResult.ACCEPTED);
        assertEquals(dropDisk(player1.getPlayerId(), 0) , DropDiskResult.ACCEPTED);
        assertEquals(dropDisk(player2.getPlayerId(), 1) , DropDiskResult.ACCEPTED);
        assertEquals(dropDisk(player1.getPlayerId(), 0) , DropDiskResult.ACCEPTED);
        assertEquals(dropDisk(player2.getPlayerId(), 1) , DropDiskResult.ACCEPTED);
        assertEquals(dropDisk(player1.getPlayerId(), 0) , DropDiskResult.ACCEPTED);
        
        assertEquals(dropDisk(player2.getPlayerId(), 1) , DropDiskResult.GAME_ALREADY_OVER);
        
        assertEquals(pollGame(player1.getGameId()).getState() , State.GAME_OVER_WINNER_PLAYER_1);
        
        
    }
    
    //TODO: More exhaustive tests for more conditions
    
}
