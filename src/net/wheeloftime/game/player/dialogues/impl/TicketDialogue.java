package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.player.content.TicketSystem;
import net.wheeloftime.game.player.content.TicketSystem.TicketEntry;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class TicketDialogue extends Dialogue {

	private String reason;

	@Override
	public void start() {
		sendDialogue("Welcome to the Ticket Control Centre, please select your inquary. Please be warned, abuse of this system will result in a ban or jail. These punishments cannot be appealed.");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (stage == -1) {
			sendOptionsDialogue("Please select an option.",
					"General Questions (Ingame)", "Password Recovery",
					"Player Report / Bug report", "Other", "Cancel");
			stage = 0;
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				reason = "General Questions";
			} else if (componentId == OPTION_2) {
				reason = "Password Recovery";
			} else if (componentId == OPTION_3) {
				reason = "Player Report";
			} else if (componentId == OPTION_4) {
				player.getTemporaryAttributtes().put("ticket_other", true);
				player.getPackets().sendInputLongTextScript(
						"Please write your 'other' reason.");
			}
			if (componentId != OPTION_4 && componentId != OPTION_5) {
				if (!player.isSupport()) {
					TicketSystem.addTicket(new TicketEntry(player, reason));
					sendDialogue("Your ticket has been submitted.");
				} else
					TicketSystem.openNextTicket(player);
			} else
				end();
			stage = 1;
		} else if (stage == 1) {
			end();
		}
	}

	@Override
	public void finish() {

	}
}
