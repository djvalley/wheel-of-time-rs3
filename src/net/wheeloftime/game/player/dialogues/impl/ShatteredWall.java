package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.content.FadingScreen;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class ShatteredWall extends Dialogue {

	/**
	 * @author: miles M
	 */

	@Override
	public void start() {
		sendOptionsDialogue(DEFAULT_OPTIONS_TITLE, "Leave the chambers.",
				"Go to Guthix's shrine.");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		switch (componentId) {
		case OPTION_1:
			FadingScreen.unfade(player,
					FadingScreen.fade(player, FadingScreen.TICK / 2),
					new Runnable() {
						@Override
						public void run() {
							player.setNextWorldTile(new WorldTile(2702, 3372, 0));
						}
					});
			end();
			break;
		case OPTION_2:
			FadingScreen.unfade(player,
					FadingScreen.fade(player, FadingScreen.TICK / 2),
					new Runnable() {
						@Override
						public void run() {
							player.setNextWorldTile(new WorldTile(1923, 5987, 0));
						}
					});
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}
}