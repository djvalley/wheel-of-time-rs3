package net.wheeloftime.game.player.dialogues.impl.skills.crafting;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.crafting.BattlestaffCrafting;
import net.wheeloftime.game.player.actions.skills.crafting.BattlestaffCrafting.CraftStaffAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class BattlestaffCraftingD extends Dialogue {
	private Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		CraftStaffAction craft = (CraftStaffAction) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, craft
				.getProducedBar().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		CraftStaffAction craft = CraftStaffAction.getBarByProduce(result
				.getProduce());
		if (craft == null)
			return;
		player.getActionManager().setAction(
				new BattlestaffCrafting(craft, item, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
