package net.wheeloftime.game.player.dialogues.impl.dungeoneering;

import net.wheeloftime.game.player.controllers.DungeonController;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class DungeonExit extends Dialogue {

	private DungeonController dungeon;

	@Override
	public void start() {
		dungeon = (DungeonController) parameters[0];
		sendDialogue(
				"This ladder leads back to the surface. You will not be able",
				"to come back to this dungeon if you leave.");

	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (stage == -1) {
			sendOptionsDialogue("Leave the dungeon and return to the surface?",
					"Yes.", "No.");
			stage = 0;
		} else if (stage == 0) {
			if (componentId == OPTION_1)
				dungeon.leaveDungeon();
			end();
		}
	}

	@Override
	public void finish() {

	}

}
