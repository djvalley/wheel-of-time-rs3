package net.nocturne.game.player.dialogues.impl.dungeoneering;

import net.nocturne.game.Animation;
import net.nocturne.game.Hit;
import net.nocturne.game.Hit.HitLook;
import net.nocturne.game.player.dialogues.Dialogue;

public class ColouredRecessShelvesD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an Option", "Blue vial.", "Green Vial.",
				"Yellow Vial.", "Violet Vial.");

	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (Math.random() < 0.2) {
			player.getPackets().sendGameMessage(
					"The vial reacts explosively as you pick it up.");
			player.applyHit(new Hit(player,
					(int) (player.getMaxHitpoints() * 0.25D),
					HitLook.REGULAR_DAMAGE));
			end();
			return;
		} else if (componentId == OPTION_1) {
			player.getInventory().addItem(19869, 1);
		} else if (componentId == OPTION_2) {
			player.getInventory().addItem(19871, 1);
		} else if (componentId == OPTION_3) {
			player.getInventory().addItem(19873, 1);
		} else if (componentId == OPTION_4) {
			player.getInventory().addItem(19875, 1);
		}
		player.setNextAnimation(new Animation(832));
		end();
	}

	@Override
	public void finish() {

	}

}
