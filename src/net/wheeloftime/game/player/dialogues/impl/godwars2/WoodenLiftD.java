package net.wheeloftime.game.player.dialogues.impl.godwars2;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class WoodenLiftD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("ascend to the desert?", "Yes, I'd like to leave.",
				"No, not right now.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		switch (stage) {
		case 1:
			switch (componentId) {
			case OPTION_1:
				player.setNextWorldTile(new WorldTile(3382, 2877, 0));
				player.getInterfaceManager().removeInterface(1746);
				player.getInterfaceManager().removeInterface(1648);
				player.getControllerManager().forceStop();
				end();
				break;
			case OPTION_2:
				end();
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {
	}

}
