package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.cache.loaders.ItemDefinitions;
import net.wheeloftime.game.player.actions.skills.prayer.PrayerBooks;
import net.wheeloftime.game.player.dialogues.Dialogue;
import net.wheeloftime.utils.ShopsHandler;

public class Jossik extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827,
				"Hello again, adventurer. What brings you this way?");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue(DEFAULT_OPTIONS_TITLE, "Can I see your wares?",
					"Have you found any new prayer books?");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				stage = 1;
				sendPlayerDialogue(9827, "Can I see your wares?");
			} else {
				for (int i = 0; i < player.getPrayerBook().length; i++) {
					if (player.getPrayerBook()[i]
							&& !player
									.containsOneItem(PrayerBooks.BOOKS[i] + 1)) {
						sendNPCDialogue(
								npcId,
								9827,
								"As a matter of fact i did! I found a "
										+ ItemDefinitions.getItemDefinitions(
												PrayerBooks.BOOKS[i] + 1)
												.getName()
										+ ", and I recognised it as yours immediately! Here you go!");
						player.getInventory().addItem(PrayerBooks.BOOKS[i] + 1,
								1);
						stage = -2;
						return;
					}
				}
				ShopsHandler.openShop(player, 35);
				end();
			}
		} else if (stage == 1) {
			stage = 2;
			sendNPCDialogue(npcId, 9827,
					"Sure thing! I think you'll agree, my prices are remarkable.");
		} else if (stage == 2) {
			ShopsHandler.openShop(player, 36);
			end();
		} else
			end();

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
