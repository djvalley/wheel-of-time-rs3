package net.wheeloftime.game.player.dialogues.impl.skills;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.player.actions.skills.cooking.Cooking;
import net.wheeloftime.game.player.actions.skills.cooking.Cooking.Cookables;
import net.wheeloftime.game.player.content.SkillsDialogue;
import net.wheeloftime.game.player.content.SkillsDialogue.SkillDialogueResult;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class CookingD extends Dialogue {

	// private Cookables cooking;
	private WorldObject object;

	@Override
	public void start() {
		Cookables cooking = (Cookables) parameters[0];
		this.object = (WorldObject) parameters[1];
		if (cooking == Cookables.RAW_MEAT) {
			end();
			player.getDialogueManager().startDialogue("MeatDrying", object);
			return;
		}
		SkillsDialogue.sendSkillDialogueByProduce(player, cooking.getProduct()
				.getId());
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		SkillDialogueResult result = SkillsDialogue.getResult(player);
		end();
		Cookables cooking = Cooking.getCookForProduce(result.getProduce());
		if (cooking == null)
			return;
		player.getActionManager().setAction(
				new Cooking(object, cooking.getRawItem(), result.getQuantity(),
						cooking));
	}

	@Override
	public void finish() {

	}

}
