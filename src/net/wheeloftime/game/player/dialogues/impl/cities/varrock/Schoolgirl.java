package net.wheeloftime.game.player.dialogues.impl.cities.varrock;

import net.wheeloftime.game.player.dialogues.Dialogue;

public class Schoolgirl extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, HAPPY, "*cough* It's so dusty in here.");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		switch (stage) {
		case -1:
			end();
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
