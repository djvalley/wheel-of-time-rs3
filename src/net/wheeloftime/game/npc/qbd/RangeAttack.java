package net.wheeloftime.game.npc.qbd;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.prayer.Prayer;
import net.wheeloftime.game.player.content.Combat;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

/**
 * Handles the Queen Black Dragon's range attack.
 * 
 * @author Emperor
 * 
 */
public final class RangeAttack implements QueenAttack {

	/**
	 * The animation.
	 */
	private static final Animation ANIMATION = new Animation(16718);

	@Override
	public int attack(final QueenBlackDragon npc, final Player victim) {
		npc.setNextAnimation(ANIMATION);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				stop();
				int hit = Utils.random(Utils.random(1400) + 500, 2990);
				if (victim.getPrayer().isActive(Prayer.SAP_STRENGTH)) {
					victim.setNextAnimation(new Animation(12573));
					victim.setNextGraphics(new Graphics(2229));
					victim.getPackets()
							.sendGameMessage(
									"You are unable to reflect damage back to this creature.");
					hit /= 2;
				} else if (victim.getPrayer().isActive(Prayer.RAPID_RENEWAL)) {
					victim.setNextAnimation(new Animation(Combat
							.getDefenceEmote(victim)));
					hit /= 2;
				} else {
					victim.setNextAnimation(new Animation(Combat
							.getDefenceEmote(victim)));
				}
				victim.applyHit(new Hit(npc, hit, hit == 0 ? HitLook.MISSED
						: HitLook.RANGE_DAMAGE));
			}
		}, 1);
		return Utils.random(4, 15);
	}

	@Override
	public boolean canAttack(QueenBlackDragon npc, Player victim) {
		return true;
	}

}