package net.wheeloftime.game.player.events.impl;

import net.wheeloftime.Settings;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.events.Event;

public class SummerEvent extends Event {

	/**
	 * @author: miles M
	 */

	private WorldTile INSIDE = new WorldTile(0, 0, 0);
	private WorldTile OUTSIDE = Settings.HOME_LOCATION;

	@Override
	public void start() {
		player.setNextWorldTile(INSIDE);
	}

	@Override
	public void stop() {
		player.setNextWorldTile(OUTSIDE);
		stopEvent();
	}
}