package net.wheeloftime.game.player.dialogues.impl.cities.varrock;

import net.wheeloftime.game.player.dialogues.Dialogue;

public class Hops extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, DRUNK, "Hops don't wanna talk now.",
				"Try different world, HIC!");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		end();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
