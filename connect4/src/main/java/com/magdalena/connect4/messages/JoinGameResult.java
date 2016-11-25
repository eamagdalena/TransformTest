package com.magdalena.connect4.messages;

import javax.xml.bind.annotation.XmlRootElement;

import com.magdalena.connect4.model.Color;
import com.magdalena.connect4.model.Player;

@XmlRootElement
public class JoinGameResult {
	
	private boolean connectionAccepted;
	
	private Color color;
	
	private long playerId;
	
	private String errorMessage;
	
	public static JoinGameResult ERROR(String message) {
		JoinGameResult r = new JoinGameResult();
		r.connectionAccepted = false;
		r.setErrorMessage(message);
		return r;
	}
	
	public static JoinGameResult ACCEPTED(Player player) {
		JoinGameResult r = new JoinGameResult();
		r.playerId = player.getId();
		r.color = player.getColor();
		r.connectionAccepted = true;
		return r;
	}

	/* -- GET & SET -- */
	
	public JoinGameResult() {
		
	}
		
	public boolean isConnectionAccepted() {
		return connectionAccepted;
	}

	public void setConnectionAccepted(boolean connectionAccepted) {
		this.connectionAccepted = connectionAccepted;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
}
