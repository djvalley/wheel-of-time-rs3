package net.wheeloftime.game.player.controllers.trollinvasion;

import net.wheeloftime.game.player.dialogues.Dialogue;

public class TrollInvasionStart extends Dialogue {

	private int click;

	@Override
	public void start() {
		click = (int) parameters[1];

	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		switch (click) {
		case 1:
			doDialogueClickOne(interfaceId, componentId);
			break;
		case 2:
			doDialogueClickTwo(interfaceId, componentId);
		case 3:
			doDialogueClickThree(interfaceId, componentId);
		}

	}

	@Override
	public void finish() {

	}

	public void doDialogueClickOne(int interfaceId, int componentId) {

	}

	public void doDialogueClickTwo(int interfaceId, int componentId) {

	}

	public void doDialogueClickThree(int interfaceId, int componentId) {

	}

}
