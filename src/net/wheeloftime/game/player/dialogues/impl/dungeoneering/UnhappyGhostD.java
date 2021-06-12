package net.wheeloftime.game.player.dialogues.impl.dungeoneering;

import net.wheeloftime.game.player.actions.skills.dungeoneering.rooms.puzzles.UnhappyGhostRoom;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class UnhappyGhostD extends Dialogue {

	@Override
	public void start() {
		UnhappyGhostRoom puzzle = (UnhappyGhostRoom) parameters[0];
		sendNPCDialogue(UnhappyGhostRoom.GHOST, puzzle.isComplete() ? NORMAL
				: SAD, "Woooo wooooo woooooooo wooo");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		end();
	}

	@Override
	public void finish() {

	}

}
