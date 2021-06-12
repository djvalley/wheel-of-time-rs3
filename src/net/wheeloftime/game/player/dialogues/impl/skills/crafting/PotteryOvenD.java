package net.wheeloftime.game.player.dialogues.impl.skills.crafting;

import net.wheeloftime.game.player.actions.skills.crafting.PotteryOven;
import net.wheeloftime.game.player.actions.skills.crafting.PotteryOven.FinishPot;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class PotteryOvenD extends Dialogue {

	@Override
	public void start() {
		FinishPot pot = (FinishPot) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, pot.getProducedPot()
				.getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		FinishPot pot = FinishPot.getPotByProduce(result.getProduce());
		if (pot == null)
			return;
		player.getActionManager().setAction(
				new PotteryOven(pot, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
