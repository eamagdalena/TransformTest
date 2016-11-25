package com.magdalena.rockpaperscissor.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Move {
	
	private List<Move> beats;
	
	private String name;
	
	private Move(String name, List<Move> beats) {
		this.name = name;
		this.beats = beats;
	}
	
	private Move(String name){
		this.name = name;
	}
	
	public boolean beats(Move otherMove) {
		return beats.contains(otherMove);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/* -- STATIC -- */
		
	private final static Map<String, Move> moves = new HashMap<>();
	
	public final static Move of(String name) {
		return moves.get(name);
	}
		
	public final static Move ROCK = new Move("Rock");
	public final static Move PAPER = new Move("Paper");
	public final static Move SCISSORS = new Move("Scissors", Arrays.asList(PAPER));
	public final static Move SPOCK = new Move("Spock", Arrays.asList(ROCK, SCISSORS));
	public final static Move LIZARD = new Move("Lizard", Arrays.asList(SPOCK, PAPER));
		
	static {
		ROCK.beats = Arrays.asList(SCISSORS, LIZARD);
		PAPER.beats = Arrays.asList(ROCK, SPOCK);
	
		moves.put(ROCK.name, ROCK);
		moves.put(PAPER.name, PAPER);
		moves.put(SCISSORS.name, SCISSORS);
		moves.put(SPOCK.name, SPOCK);
		moves.put(LIZARD.name, LIZARD);
	}
}
