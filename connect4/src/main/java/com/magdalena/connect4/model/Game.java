package com.magdalena.connect4.model;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game {

	public final static int COLUMNS = 7;
	public final static int ROWS = 6;
	public final static int SIZE_TO_WIN = 4;
	
	public enum State { 
		WAITING_PLAYER_2, 
		PLAYER_1_TURN, 
		PLAYER_2_TURN, 
		GAME_OVER_WINNER_PLAYER_1, 
		GAME_OVER_WINNER_PLAYER_2, 
		GAME_OVER_TIE
		} 
		
	private long id;
		
	private State state = State.WAITING_PLAYER_2; 

	private Player player1, player2;
	
	private Color[] board = new Color[COLUMNS * ROWS];
	
	public Game() {
	
	}

	/** Returns the row in which the disk fell, -1 if column is full */
	public int dropDisk(Color color, int column) {
		
		for(int row = 0; row < ROWS; row++){
			if(board[row * COLUMNS + column] == null){
				board[row * COLUMNS + column] = color;
				return row;
			}
		}
		
		return -1;
	}
		
	public boolean isGameATie(){
		/* We want to check that last row has no nulls */
		return Arrays.stream(board, board.length - COLUMNS, board.length).allMatch((c) -> c != null);
	}
	
	/** Starting from this point, we check the 5 possible directions */
	public boolean isGameWon(int row, int column){
				
		boolean evaluateDownwards = row >= SIZE_TO_WIN - 1;

		int startingPoint = row * COLUMNS + column;
		
		final Color color = board[startingPoint];
		
		Predicate<Color> matchColor = c -> c == color;
		
		if(column >= SIZE_TO_WIN - 1) {
		
			/* LEFT */
			if(Arrays.stream(board, startingPoint - SIZE_TO_WIN - 1, startingPoint).allMatch(matchColor)){
				return true;
			}

			/* LEFT DOWN */
			if(evaluateDownwards &&	
					IntStream.rangeClosed(1, SIZE_TO_WIN-1)
					.mapToObj(i -> board[startingPoint - COLUMNS * i - i])
					.allMatch(matchColor)){
				return true;
			}
		}

		if(column <= COLUMNS - SIZE_TO_WIN){
		
			/* RIGHT */
			if(Arrays.stream(board, startingPoint + 1, startingPoint + SIZE_TO_WIN).allMatch(matchColor)){
				return true;
			}
			
			/* RIGHT DOWN */
			if(evaluateDownwards &&	
					IntStream.rangeClosed(1, SIZE_TO_WIN-1)
					.mapToObj(i -> board[startingPoint - COLUMNS * i + i])
					.allMatch(matchColor)){
				return true;
			}
		}
	
		/* DOWN */
		if(evaluateDownwards &&	
				IntStream.rangeClosed(1, SIZE_TO_WIN-1)
				.mapToObj(i -> board[startingPoint - COLUMNS * i])
				.allMatch(matchColor)){
			return true;
		}
				
		return false;
	}
	
	/* --- */
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Color[] getBoard() {
		return board;
	}

	public void setBoard(Color[] board) {
		this.board = board;
	}
		
}
