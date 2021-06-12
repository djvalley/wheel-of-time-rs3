package net.wheeloftime.game.player.cutscenes.actions;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

public class NPCAnimationAction extends CutsceneAction {

	private Animation anim;

	public NPCAnimationAction(int cachedObjectIndex, Animation anim,
			int actionDelay) {
		super(cachedObjectIndex, actionDelay);
		this.anim = anim;
	}

	@Override
	public void process(Player player, Object[] cache) {
		NPC npc = (NPC) cache[getCachedObjectIndex()];
		npc.setNextAnimation(anim);
	}

}
