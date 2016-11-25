package com.magdalena.rockpaperscissor.dao;

import java.util.HashMap;
import java.util.Map;

import com.magdalena.rockpaperscissor.model.Game;

public class GameDAO {

	/* "DB" */
	private Map<Long, Game> playersGames = new HashMap<>(); 
	
	public synchronized long newPlayer(){
		long playerId = System.currentTimeMillis(); /* Simple */
		
		playersGames.put(playerId, new Game());
		
		return playerId;
	}
	
	public Game findGameByPlayerId(long playerId) {
		return playersGames.get(playerId);
	}
}
