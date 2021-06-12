package net.wheeloftime.game.npc.araxxor;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.Combat;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class MeleeAttack implements AraxxorAttack {

	@Override
	public int attack(final Araxxor npc, final Player victim) {
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				victim.AraxxorAttackCount++;
				stop();
				int hit = 0;
				/*
				 * if (victim.getPrayer().usingPrayer(0, 19)) {
				 * npc.setNextAnimation(new Animation (24046));
				 * victim.setNextAnimation(new
				 * Animation(Combat.getDefenceEmote(victim))); //hit = 0; hit =
				 * Utils.random(0 + Utils.random(150), 360); } else {
				 */
				hit = Utils.random(0 + Utils.random(1050), 3000);
				npc.setNextAnimation(new Animation(24046));
				victim.setNextAnimation(new Animation(Combat
						.getDefenceEmote(victim)));
				// }
				victim.applyHit(new Hit(victim, hit, hit == 0 ? HitLook.MISSED
						: HitLook.MELEE_DAMAGE));
			}
		});
		// return Utils.random(4, 6);
		return 6;
	}

	@Override
	public boolean canAttack(Araxxor npc, Player victim) {
		return victim.getY() > npc.getBase().getY() + 10;
	}

}
