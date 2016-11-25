package com.magdalena.connect4.messages;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateGameResult {

	/* To share with your friend */
	private long gameId;

	private long playerId;
	
	public CreateGameResult() {
	
	}
	
	public CreateGameResult(long gameId, long playerId) {
		this.gameId = gameId;
		this.playerId = playerId;
	}
	
	/* --- */
	
	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	
	
}
