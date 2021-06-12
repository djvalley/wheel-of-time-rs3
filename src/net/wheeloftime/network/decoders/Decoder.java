package net.wheeloftime.network.decoders;

import net.wheeloftime.network.Session;
import net.wheeloftime.stream.InputStream;

public abstract class Decoder {

	protected Session session;

	public Decoder(Session session) {
		this.session = session;
	}

	public abstract int decode(InputStream stream);

}