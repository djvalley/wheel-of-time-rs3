package net.wheeloftime.game.player.quest;

import net.wheeloftime.game.player.Player;

public class Entry {

	private String text;
	private String finished;

	public Entry(String text) {
		this.text = "<col=08088A>" + text;
		this.finished = text; // "<col=000000>" +
	}

	public boolean meetsCondition(Player player) {
		return false;
	}

	public String getText() {
		return text;
	}

	public String getFinished(Player player) {
		return meetsCondition(player) ? finished : text;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}
}