package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.controllers.RuneEssenceController;
import net.wheeloftime.game.player.dialogues.Dialogue;
import net.wheeloftime.utils.ShopsHandler;

public class Aubury extends Dialogue {

	private NPC npc;

	@Override
	public void start() {
		npc = (NPC) parameters[0];
		sendNPCDialogue(npc.getId(), 9827, "Do you want to buy some runes?");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(DEFAULT_OPTIONS_TITLE, "Yes, please.",
					"No thanks.", "Can you teleport me to the rune essence?");
			break;
		case 0:
			switch (componentId) {
			case OPTION_1:
				ShopsHandler.openShop(player, 184);
				end();
				break;
			case OPTION_2:
				stage = -2;
				sendPlayerDialogue(9827, "No thanks.");
				break;
			case OPTION_3:
				stage = 1;
				sendPlayerDialogue(9827,
						"Can you teleport me to the rune essence?");
				break;
			default:
				end();
				break;
			}
			break;
		case 1:
			RuneEssenceController.teleport(player, npc);
			end();
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
