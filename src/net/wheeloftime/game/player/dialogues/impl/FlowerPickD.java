package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class FlowerPickD extends Dialogue {

	WorldObject object;

	@Override
	public void start() {
		object = (WorldObject) this.parameters[0];
		player.faceObject(object);
		sendOptionsDialogue("What do you want to do with the flowers?", "Pick",
				"Leave them");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (componentId == OPTION_1) {
			player.setNextAnimation(new Animation(827));
			player.getInventory().addItemDrop(
					2460 + ((object.getId() - 2980) * 2), 1);
			World.removeObject(object);
		}
		end();
	}

	@Override
	public void finish() {

	}
}
