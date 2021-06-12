package net.wheeloftime.game.player.dialogues.impl.skills.magic;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.magic.BoltEnchanting;
import net.wheeloftime.game.player.actions.skills.magic.BoltEnchanting.EnchantAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class BoltEnchantingD extends Dialogue {

	private Item item;

	@Override
	public void start() {
		EnchantAction enchant = (EnchantAction) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, enchant
				.getProducedBow().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		EnchantAction enchant = EnchantAction.getBarByProduce(result
				.getProduce());
		if (enchant == null)
			return;
		player.getActionManager().setAction(
				new BoltEnchanting(enchant, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}