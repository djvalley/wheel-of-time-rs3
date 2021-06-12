package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.player.content.activities.minigames.CastleWars;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class CastleWarsScoreboard extends Dialogue {

	@Override
	public void start() {
		CastleWars.viewScoreBoard(player);

	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		end();

	}

	@Override
	public void finish() {

	}

}
