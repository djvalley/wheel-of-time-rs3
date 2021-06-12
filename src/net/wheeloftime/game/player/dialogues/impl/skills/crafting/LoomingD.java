package net.wheeloftime.game.player.dialogues.impl.skills.crafting;

import net.wheeloftime.game.player.actions.skills.crafting.Looming;
import net.wheeloftime.game.player.actions.skills.crafting.Looming.CreateCape;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class LoomingD extends Dialogue {

	@Override
	public void start() {
		CreateCape cape = (CreateCape) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, cape.getProducedBar()
				.getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		CreateCape cape = CreateCape.getBarByProduce(result.getProduce());
		if (cape == null)
			return;
		player.getActionManager().setAction(
				new Looming(cape, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
