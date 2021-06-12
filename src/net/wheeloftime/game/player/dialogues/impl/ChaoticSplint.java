package net.wheeloftime.game.player.dialogues.impl;

import net.wheeloftime.game.item.actions.SmithingCombinations;
import net.wheeloftime.game.item.actions.SmithingCombinations.Combos;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class ChaoticSplint extends Dialogue {

	@Override
	public void finish() {

	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (componentId == OPTION_1)
			SmithingCombinations.processCombo(player, Combos.ANCIENT_DEFENDER);
		else if (componentId == OPTION_2)
			SmithingCombinations.processCombo(player, Combos.ANCIENT_REPRISER);
		else if (componentId == OPTION_3)
			SmithingCombinations.processCombo(player, Combos.ANCIENT_LANTERN);
		end();
	}

	@Override
	public void start() {
		sendOptionsDialogue("Choose an Option", "Ancient Defender",
				"Ancient Repriser", "Ancient Lantern", "None");
	}

}