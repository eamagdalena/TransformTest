package com.magdalena.connect4.dao;

import java.util.HashMap;
import java.util.Map;

import com.magdalena.connect4.model.Game;
import com.magdalena.connect4.model.Player;

public class Connect4DAO {

	/* "DB" */
	private Map<Long, Game> playersGames = new HashMap<>(); 
	private Map<Long, Game> games = new HashMap<>();
		
	private long generateId(){
		return System.currentTimeMillis(); /* Simple */
	}
	
	public Game findGameByPlayerId(long playerId) {
		return playersGames.get(playerId);
	}
	
	public Game findGameById(long gameId) {
		return games.get(gameId);
	}
	
	public long saveGame(Game game) {
		long id;

		synchronized(games){
			id = generateId();
			games.put(id, game);			
		}

		game.setId(id);
		
		return id;
	}

	public void savePlayerGame(Player player, Game game){
		synchronized(playersGames){
			playersGames.put(player.getId(), game);	
		}
	}
	
	public Player newPlayer() {
		Player p = new Player();
		
		synchronized(this){
			p.setId(generateId());
		}
		
		return p;
	}
	
}
