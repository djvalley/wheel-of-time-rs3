package net.wheeloftime.game.player.cutscenes.actions;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.player.Player;

public class PlayerAnimationAction extends CutsceneAction {

	private Animation anim;

	public PlayerAnimationAction(Animation anim, int actionDelay) {
		super(-1, actionDelay);
		this.anim = anim;
	}

	@Override
	public void process(Player player, Object[] cache) {
		player.setNextAnimation(anim);
	}

}
