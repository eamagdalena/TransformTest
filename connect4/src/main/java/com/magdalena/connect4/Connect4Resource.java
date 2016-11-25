package com.magdalena.connect4;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.magdalena.connect4.messages.CreateGameResult;
import com.magdalena.connect4.messages.DropDiskResult;
import com.magdalena.connect4.messages.JoinGameResult;
import com.magdalena.connect4.model.Game;
import com.magdalena.connect4.service.Connect4Service;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("game")
public class Connect4Resource {

	private static Connect4Service connect4 = new Connect4Service();

    @GET
    @Path("/create/{myColor}")
    @Produces(MediaType.APPLICATION_JSON)
    public CreateGameResult createGame(@PathParam("myColor") String player1Color) {
        return connect4.createGame(player1Color);
    }
    
    @GET
    @Path("/{gameId}/join")
    @Produces(MediaType.APPLICATION_JSON)
    public JoinGameResult joinGame(@PathParam("gameId") long gameId) {
        return connect4.joinGame(gameId);
    }
    
    @GET
    @Path("/{gameId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Game gameStatus(@PathParam("gameId") long gameId) {
        return connect4.pollState(gameId);
    }
    
    @GET
    @Path("/player/{playerId}/dropOnColumn/{column}")
    @Produces(MediaType.TEXT_PLAIN)
    public String dropOnColumn(@PathParam("playerId") long playerId, @PathParam("column") int column) {
        return connect4.dropDisk(playerId, column).toString();
    }
        
}
