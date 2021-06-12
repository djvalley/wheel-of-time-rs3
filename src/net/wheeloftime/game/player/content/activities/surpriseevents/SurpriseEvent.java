package net.wheeloftime.game.player.content.activities.surpriseevents;

import net.wheeloftime.game.player.Player;

public interface SurpriseEvent {

	/**
	 * Start's event.
	 */
	void start();

	/**
	 * Trie's to join the event.
	 */
	void tryJoin(Player player);
}
