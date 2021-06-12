package net.wheeloftime.game.player.dialogues.impl.skills.fletching;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.actions.skills.fletching.BoltFletching;
import net.wheeloftime.game.player.actions.skills.fletching.BoltFletching.FletchBoltAction;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class BoltFletchingD extends Dialogue {
	private Item item;

	@Override
	public void start() {
		item = (Item) parameters[0];
		FletchBoltAction fletch = (FletchBoltAction) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, fletch
				.getProducedBow().getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		FletchBoltAction fletch = FletchBoltAction.getBarByProduce(result
				.getProduce());
		if (fletch == null)
			return;
		player.getActionManager().setAction(
				new BoltFletching(fletch, item, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}
