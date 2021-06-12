package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.cooking.Cooking;
import net.wheeloftime.game.player.actions.skills.cooking.Cooking.Cookables;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class MeatDrying extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("What would you like to do?", "Cook the meat.",
				"Dry the meat.");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (stage == -1) {
			player.getActionManager().setAction(
					new Cooking((WorldObject) this.parameters[0], new Item(
							2132, 1), 28,
							componentId == OPTION_1 ? Cookables.RAW_MEAT
									: Cookables.SINEW));
			end();
		}
	}

	@Override
	public void finish() {

	}
}
