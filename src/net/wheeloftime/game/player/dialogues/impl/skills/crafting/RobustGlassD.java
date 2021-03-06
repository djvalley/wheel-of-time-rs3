package net.wheeloftime.game.player.dialogues.impl.skills.crafting;

import net.wheeloftime.game.player.actions.skills.crafting.RobustGlass;
import net.wheeloftime.game.player.actions.skills.crafting.RobustGlass.CreateGlass;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class RobustGlassD extends Dialogue {

	@Override
	public void start() {
		System.out.println("l0l");
		CreateGlass glass = (CreateGlass) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, glass
				.getProducedBar().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		CreateGlass bar = CreateGlass.getBarByProduce(result.getProduce());
		if (bar == null)
			return;
		player.getActionManager().setAction(
				new RobustGlass(bar, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
