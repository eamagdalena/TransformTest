package com.magdalena.rockpaperscissor.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Result {

	public static enum OUTCOME { WIN, LOSE, TIE};
		
	private OUTCOME outcome; 
	private String aiMove; 
	private int playerWins;
		
	public Result() {
	
	}
	
	public Result(OUTCOME outcome, String aiMove, int playerWins){
		this.aiMove = aiMove;
		this.outcome = outcome;
		this.setPlayerWins(playerWins);
	}
	
	/* SET & GET */
	
	public OUTCOME getOutcome() {
		return outcome;
	}

	public void setOutcome(OUTCOME outcome) {
		this.outcome = outcome;
	}

	public String getAiMove() {
		return aiMove;
	}

	public void setAiMove(String aiMove) {
		this.aiMove = aiMove;
	}

	public int getPlayerWins() {
		return playerWins;
	}

	public void setPlayerWins(int playerWins) {
		this.playerWins = playerWins;
	}

}
