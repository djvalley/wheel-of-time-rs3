package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.item.ItemConstants;
import net.wheeloftime.game.player.content.grandExchange.GrandExchange;
import net.wheeloftime.game.player.controllers.Wilderness;
import net.wheeloftime.game.player.dialogues.Dialogue;
import net.wheeloftime.utils.Logger;
import net.wheeloftime.utils.Utils;

public class HighValueItemOption extends Dialogue {

	public Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		sendOptionsDialogue(
				"Drop " + item.getName() + " (worth:"
						+ Utils.format(GrandExchange.getPrice(item.getId()))
						+ " gp)?", "Yes, drop it!", "No, don't!");
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (componentId == OPTION_1) {
			player.getInventory().deleteItem(item);
			if (player.getCharges().degradeCompletely(item, true) != -1)
				item.setId(player.getCharges().degradeCompletely(item, false));
			if (player.isBeginningAccount()) {
				World.addGroundItem(item, new WorldTile(player), player, true,
						60, 2, 0);
			} else if (player.getControllerManager().getController() instanceof Wilderness
					&& ItemConstants.isTradeable(item))
				World.addGroundItem(item, new WorldTile(player), player, false,
						-1);
			else
				World.addGroundItem(item, new WorldTile(player), player, true,
						60);
			Logger.globalLog(player.getUsername(), player.getSession().getIP(),
					new String(" has dropped item [ id: " + item.getId()
							+ ", amount: " + item.getAmount() + " ]."));
			end();
		} else if (componentId == OPTION_2) {
			end();
		}
	}

	@Override
	public void finish() {

	}
}