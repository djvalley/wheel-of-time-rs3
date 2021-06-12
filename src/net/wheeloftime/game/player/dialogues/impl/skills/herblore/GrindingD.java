package net.wheeloftime.game.player.dialogues.impl.skills.herblore;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.herblore.Grinding;
import net.wheeloftime.game.player.actions.skills.herblore.Grinding.GrindAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

/**
 * author: Tommeh
 */

public class GrindingD extends Dialogue {
	private Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		GrindAction herb = (GrindAction) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, herb
				.getProducedHerb().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		GrindAction herb = GrindAction.getHerbByProduce(result.getProduce());
		if (herb == null)
			return;
		player.getActionManager().setAction(
				new Grinding(herb, item, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
