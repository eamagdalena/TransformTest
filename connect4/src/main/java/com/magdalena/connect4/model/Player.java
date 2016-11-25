package com.magdalena.connect4.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Player {

	private Color color;
	
	private long id;

	public Player() {
	
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
