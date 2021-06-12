package net.wheeloftime.game.player.dialogues.impl.skills.herblore;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.herblore.Herblore;
import net.wheeloftime.game.player.actions.skills.herblore.Herblore.CleanAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

/**
 * author: Tommeh
 */

public class HerbloreD extends Dialogue {

	private Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		CleanAction herb = (CleanAction) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, herb
				.getProducedHerb().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		CleanAction herb = CleanAction.getHerbByProduce(result.getProduce());
		if (herb == null)
			return;
		player.getActionManager().setAction(
				new Herblore(herb, item, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
