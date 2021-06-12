package net.wheeloftime.game.player.dialogues.impl.skills.summoning;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.player.actions.skills.summoning.SpecialScrolls;
import net.wheeloftime.game.player.actions.skills.summoning.SpecialScrolls.Scroll;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class SpecialScrollsD extends Dialogue {

	private WorldObject object;

	@Override
	public void start() {
		object = (WorldObject) parameters[0];
		Scroll pouch = (Scroll) parameters[1];
		SkillsDialogue.sendSkillDialogueByProduce(player, pouch.getRealPouchId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		Scroll pouch = Scroll.getPouchByProduce(result.getProduce());
		if(pouch == null)
			return;
		player.getActionManager().setAction(new SpecialScrolls(pouch, object, result.getQuantity()));
	}

	@Override
	public void finish() {
	}
}