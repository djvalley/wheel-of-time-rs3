package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.magic.Magic;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class FamiliarInspection extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Teleport to " + parameters[0] + "?", "Yes.", "No.");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (componentId == OPTION_1)
			Magic.sendNormalTeleportSpell(player, 1, 0,
					(WorldTile) parameters[1]);
		end();
	}

	@Override
	public void finish() {

	}
}
