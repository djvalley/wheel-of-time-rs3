package net.wheeloftime.game.player.dialogues.impl.skills.crafting;

import net.wheeloftime.game.player.actions.skills.crafting.Spinning;
import net.wheeloftime.game.player.actions.skills.crafting.Spinning.CreateSpin;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class SpinningD extends Dialogue {

	@Override
	public void start() {
		CreateSpin cape = (CreateSpin) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, cape
				.getProducedItem().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		CreateSpin cape = CreateSpin.getSpinByProduce(result.getProduce());
		if (cape == null)
			return;
		player.getActionManager().setAction(
				new Spinning(cape, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
