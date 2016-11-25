package com.magdalena.connect4.service;

import com.magdalena.connect4.dao.Connect4DAO;
import com.magdalena.connect4.messages.CreateGameResult;
import com.magdalena.connect4.messages.DropDiskResult;
import com.magdalena.connect4.messages.JoinGameResult;
import com.magdalena.connect4.model.Color;
import com.magdalena.connect4.model.Game;
import com.magdalena.connect4.model.Game.State;
import com.magdalena.connect4.model.Player;

public class Connect4Service {

	private Connect4DAO dao = new Connect4DAO();
		
	public  CreateGameResult createGame(String color) {

		Player player1 = dao.newPlayer();
		player1.setColor(Color.valueOf(color));
		
		Game game = new Game();
		game.setPlayer1(player1);
		
		dao.saveGame(game);
		dao.savePlayerGame(player1, game);
		
		return new CreateGameResult(game.getId(), player1.getId());
		
	}
	
	public JoinGameResult joinGame(long gameId) {
		
		Game game = dao.findGameById(gameId);
		
		if(game.getPlayer2() != null){
			return JoinGameResult.ERROR("Game is full!");
		}
		
		Player player2 = dao.newPlayer();

		player2.setColor(game.getPlayer1().getColor() == Color.RED ? Color.YELLOW : Color.RED);

		game.setPlayer2(player2);
		game.setState(Game.State.PLAYER_1_TURN);
		
		dao.savePlayerGame(player2, game);
		
		return JoinGameResult.ACCEPTED(player2);
	}
		
	/** We return the full game state to keep it simple */
	public Game pollState(long gameId) {
		return dao.findGameById(gameId);
	}
	
	public DropDiskResult dropDisk(long playerId, int column) {
	
		Game game = dao.findGameByPlayerId(playerId);
		
		if(game == null){
			return DropDiskResult.UNKNOWN_PLAYER;
		}
		
		boolean iAmPlayer1 = game.getPlayer1().getId() == playerId;
		
		switch(game.getState()) {
		
		case GAME_OVER_TIE:
		case GAME_OVER_WINNER_PLAYER_1:
		case GAME_OVER_WINNER_PLAYER_2:
		default:
			return DropDiskResult.GAME_ALREADY_OVER;
		
		case WAITING_PLAYER_2: 
			return DropDiskResult.WAITING_FOR_OTHER_PLAYER;
		
		case PLAYER_1_TURN:
			if(!iAmPlayer1){
				return DropDiskResult.OTHER_PLAYER_TURN;
			}
			return dropDisk(game, game.getPlayer1(), column);
		
		case PLAYER_2_TURN:
			if(iAmPlayer1){
				return DropDiskResult.OTHER_PLAYER_TURN;
			}
			return dropDisk(game, game.getPlayer2(), column);
		}
	}
	
	private DropDiskResult dropDisk(Game game, Player player, int column) {
		
		int row = game.dropDisk(player.getColor(), column);
		
		if(row == -1){
			return DropDiskResult.COLUMN_FULL;
		}
		
		if(game.isGameWon(row, column)){
			game.setState(player == game.getPlayer1() ? State.GAME_OVER_WINNER_PLAYER_1 : State.GAME_OVER_WINNER_PLAYER_2);
		}else if(game.isGameATie()){
			game.setState(State.GAME_OVER_TIE);
		}else{
			game.setState(player == game.getPlayer1() ? State.PLAYER_2_TURN : State.PLAYER_1_TURN);
		}
		
		return DropDiskResult.ACCEPTED;
	}
	
}
