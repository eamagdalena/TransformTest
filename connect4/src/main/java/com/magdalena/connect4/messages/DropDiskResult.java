package com.magdalena.connect4.messages;

public enum DropDiskResult {
	ACCEPTED,
	OTHER_PLAYER_TURN,
	COLUMN_FULL,
	GAME_ALREADY_OVER,
	WAITING_FOR_OTHER_PLAYER,
	UNKNOWN_PLAYER
}
