package com.magdalena.rockpaperscissor.model;

import com.magdalena.rockpaperscissor.ai.AI;

public class Game {
	
	private int playerWins = 0;
	
	public Result playRound(String playerMove) {
		
		Move move = Move.of(playerMove);
		
		Move aiMove = AI.nextMove();
		
		if(move == aiMove) 
			return new Result(Result.OUTCOME.TIE, aiMove.toString(), playerWins);
		else if (move.beats(aiMove))
			return new Result(Result.OUTCOME.WIN, aiMove.toString(), ++playerWins);
		else 
			return new Result(Result.OUTCOME.LOSE, aiMove.toString(), playerWins);
		
	}
	
	public int getWins() {
		return playerWins;
	}
}
