package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class CraftingD extends Dialogue {

	@Override
	public void start() {
		SkillsDialogue.sendSkillDialogueByProduce(player, 0);
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillsDialogue.getResult(player);
		end();
	}

	@Override
	public void finish() {

	}
}