package net.wheeloftime.game.player.dialogues.impl.skills.magic;

import net.wheeloftime.game.player.actions.skills.magic.JewelleryEnchanting;
import net.wheeloftime.game.player.actions.skills.magic.JewelleryEnchanting.JewelleryAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class JewelleryEnchantingD extends Dialogue {

	@Override
	public void start() {
		JewelleryAction enchant = (JewelleryAction) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, enchant
				.getProducedBow().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		JewelleryAction enchant = JewelleryAction.getBarByProduce(result
				.getProduce());
		if (enchant == null)
			return;

		player.getActionManager().setAction(
				new JewelleryEnchanting(enchant, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}