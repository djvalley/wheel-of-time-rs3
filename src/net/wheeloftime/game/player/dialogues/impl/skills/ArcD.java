package net.wheeloftime.game.player.dialogues.impl.skills;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.ArcIsland;
import net.wheeloftime.game.player.actions.ArcIsland.ArcAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

/**
 * 
 * @author Miles Black (bobismyname)
 * @date Jan 19, 2017
 */

public class ArcD extends Dialogue {

	private Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		ArcAction activity = (ArcAction) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, activity
				.getProducedItem().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		ArcAction activity = ArcAction.getByProduct(result.getProduce());
		if (activity == null)
			return;
		player.getActionManager().setAction(
				new ArcIsland(activity, item, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}