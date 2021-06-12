package net.wheeloftime.game.player.cutscenes.actions;

import net.wheeloftime.game.ForceTalk;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class NPCForceTalkAction extends CutsceneAction {

	private String text;

	public NPCForceTalkAction(int cachedObjectIndex, String text,
			int actionDelay) {
		super(cachedObjectIndex, actionDelay);
		this.text = text;
	}

	@Override
	public void process(Player player, Object[] cache) {
		NPC npc = (NPC) cache[getCachedObjectIndex()];
		npc.setNextForceTalk(new ForceTalk(text));
		Dialogue.sendNPCDialogueNoContinue(player, npc.getId(), 9827, text);
		Dialogue.sendEntityDialogueNoContinue(player, Dialogue.IS_NPC,
				npc.getName(), npc.getId(), 9827, text);
	}

}
