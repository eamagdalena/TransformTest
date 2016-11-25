package com.magdalena.rockpaperscissor.ai;

import java.util.Random;

import com.magdalena.rockpaperscissor.model.Move;

public class AI {

	private final static Random random = new Random();
	
	public final static Move nextMove() {
		switch(random.nextInt(5)){
			case 0: return Move.PAPER;
			case 1: return Move.ROCK;
			case 2: default: return Move.SCISSORS;
			case 3: return Move.SPOCK;
			case 4: return Move.LIZARD;
		}
	}
	
}
