package net.wheeloftime.game.player.dialogues.impl.skills.invention;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.invention.Invention;
import net.wheeloftime.game.player.actions.skills.invention.Invention.InventionAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

/**
 * author: Tommeh
 */

public class InventionD extends Dialogue {

	private Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		InventionAction invention = (InventionAction) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, invention
				.getProducedItem().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		InventionAction invention = InventionAction.getByProduct(result
				.getProduce());
		if (invention == null)
			return;
		player.getActionManager().setAction(
				new Invention(invention, item, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
