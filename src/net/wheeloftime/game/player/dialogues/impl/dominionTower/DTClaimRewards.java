package net.wheeloftime.game.player.dialogues.impl.dominionTower;

import net.wheeloftime.game.player.dialogues.Dialogue;

public class DTClaimRewards extends Dialogue {

	@Override
	public void start() {
		sendDialogue("You have a Dominion Factor of "
				+ player.getDominionTower().getDominionFactor() + ".");

	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue(
					"If you claim your rewards your progress will be reset.",
					"Claim Rewards", "Cancel");
		} else if (stage == 0) {
			if (componentId == OPTION_1)
				player.getDominionTower().openRewardsChest();
			end();
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
