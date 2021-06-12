package net.wheeloftime.network.encoders;

import net.wheeloftime.network.Session;

public abstract class Encoder {

	protected Session session;

	public Encoder(Session session) {
		this.session = session;
	}

}
