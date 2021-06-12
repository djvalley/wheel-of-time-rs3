package net.wheeloftime.game.player.dialogues.impl.skills;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.player.actions.skills.smithing.Smelting;
import net.wheeloftime.game.player.actions.skills.smithing.Smelting.SmeltingBar;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class SmeltingD extends Dialogue {

	private WorldObject object;

	@Override
	public void start() {
		object = (WorldObject) parameters[0];
		SmeltingBar bar = (SmeltingBar) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, bar.getProducedBar()
				.getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		SmeltingBar bar = SmeltingBar.getBarByProduce(result.getProduce());
		if (bar == null)
			return;
		player.getActionManager().setAction(
				new Smelting(bar, object, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}