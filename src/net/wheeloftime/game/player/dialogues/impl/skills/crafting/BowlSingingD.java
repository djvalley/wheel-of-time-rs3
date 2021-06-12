package net.wheeloftime.game.player.dialogues.impl.skills.crafting;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.player.actions.skills.crafting.BowlSinging;
import net.wheeloftime.game.player.actions.skills.crafting.BowlSinging.CreateCrystal;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class BowlSingingD extends Dialogue {

	private WorldObject object;

	@Override
	public void start() {
		object = (WorldObject) parameters[0];
		CreateCrystal crystal = (CreateCrystal) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, crystal
				.getProducedBar().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		CreateCrystal crystal = CreateCrystal.getBarByProduce(result
				.getProduce());
		if (crystal == null)
			return;
		player.getActionManager().setAction(
				new BowlSinging(crystal, object, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
