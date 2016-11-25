package com.magdalena.rockpaperscissor;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.magdalena.rockpaperscissor.model.Move;
import com.magdalena.rockpaperscissor.model.Result;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class RockPaperScissorsResourceTest {

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

    /** Test that we receive a valid player id */
    @Test
    public void testStart() {
        String responseMsg = target.path("rps/start").request().get(String.class);
        assertTrue(Long.parseLong(responseMsg) > 0);
    }
    
    private final static List<String> validMoves = 
    		Arrays.asList(Move.LIZARD.toString(),Move.SPOCK.toString(),Move.ROCK.toString(),Move.PAPER.toString(),Move.SCISSORS.toString());
    
    /** Test that after one play, we have 0 or 1 wins, an outcome and a known move from the AI */
    @Test
    public void testPlay() {
        String responseMsg = target.path("rps/start").request().get(String.class);
        
        System.out.println(responseMsg);
        
        Result res = target.path("rps/player/" + responseMsg + "/play/Rock").request().get(Result.class);
        
        assertTrue(validMoves.contains(res.getAiMove()));
        
        assertNotNull(res.getOutcome());
        
        assertTrue(res.getPlayerWins() >= 0 && res.getPlayerWins() < 2);
        
    }
    
}
