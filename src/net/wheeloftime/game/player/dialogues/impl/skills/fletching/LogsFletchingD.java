package net.wheeloftime.game.player.dialogues.impl.skills.fletching;

import net.wheeloftime.cache.loaders.ItemDefinitions;
import net.wheeloftime.game.player.actions.skills.fletching.LogsFletching;
import net.wheeloftime.game.player.actions.skills.fletching.LogsFletching.FletchData;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class LogsFletchingD extends Dialogue {

	@Override
	public void start() {
		FletchData fletch = (FletchData) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player,
				fletch.getProducedBow());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		System.out.println(ItemDefinitions.getItemDefinitions(
				result.getProduce()).getCSOpcode(2653));
		FletchData fletch = FletchData.getBarByProduce(result.getProduce(),
				ItemDefinitions.getItemDefinitions(result.getProduce())
						.getCSOpcode(2653));
		player.getActionManager().setAction(
				new LogsFletching(fletch, result.getProduce(), result
						.getQuantity()));
		end();
	}

	@Override
	public void finish() {
	}
}