package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.player.actions.WaterFilling;
import net.wheeloftime.game.player.actions.WaterFilling.Fill;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class WaterFillingD extends Dialogue {

	@Override
	public void start() {
		Fill fill = (Fill) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, fill.getFull());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {

		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		Fill fill = WaterFilling.getFillByProduce(result.getProduce());
		if (fill == null)
			return;
		player.getActionManager().setAction(
				new WaterFilling(fill, result.getQuantity()));

	}

	@Override
	public void finish() {

	}
}