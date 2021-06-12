package net.wheeloftime.game.player.dialogues.impl.skills.dungeoneering;

import net.wheeloftime.game.player.actions.skills.dungeoneering.skills.DungeoneeringSpinning;
import net.wheeloftime.game.player.actions.skills.dungeoneering.skills.DungeoneeringSpinning.DungSpin;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class DungeoneeringSpinningD extends Dialogue {

	@Override
	public void start() {
		DungSpin cape = (DungSpin) parameters[0];
		SkillsDialogue.sendSkillDialogueByProduce(player, cape.getProducedBar()
				.getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		DungSpin cape = DungSpin.getBarByProduce(result.getProduce());
		if (cape == null)
			return;
		player.getActionManager().setAction(
				new DungeoneeringSpinning(cape, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}