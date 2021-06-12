package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.player.dialogues.Dialogue;

public class ItemMessage extends Dialogue {

	@Override
	public void start() {
		sendEntityDialogue(IS_ITEM, "", (Integer) parameters[1], 1,
				(String) parameters[0]);
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		end();
	}

	@Override
	public void finish() {

	}

}
