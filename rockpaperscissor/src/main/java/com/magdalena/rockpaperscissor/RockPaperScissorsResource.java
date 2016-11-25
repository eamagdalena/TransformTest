package com.magdalena.rockpaperscissor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.magdalena.rockpaperscissor.dao.GameDAO;
import com.magdalena.rockpaperscissor.model.Result;

@Path("rps")
public class RockPaperScissorsResource {

	private static GameDAO dao = new GameDAO();

	@GET
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/start")
    public Long start() {
		System.out.println("Start!");
        return dao.newPlayer();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/player/{playerId}/play/{move}")
    public Result play(@PathParam("playerId") Long playerId, @PathParam("move") String move) {
    		return dao
    				.findGameByPlayerId(playerId)
    				.playRound(move);	
    }
}
