package net.wheeloftime.game.player;

import net.wheeloftime.stream.InputStream;

public class LogicPacket {

	private int id;
	InputStream stream;

	public LogicPacket(int id, InputStream stream) {
		this.id = id;
		this.stream = stream;
	}

	public int getId() {
		return id;
	}

	public InputStream getStream() {
		return stream;
	}
}