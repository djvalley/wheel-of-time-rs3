package net.wheeloftime.game.npc.qbd;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.Combat;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

/**
 * Handles the super dragonfire attack.
 * 
 * @author Emperor
 * 
 */
public final class SuperFireAttack implements QueenAttack {

	/**
	 * The animation.
	 */
	private static final Animation ANIMATION = new Animation(16745);

	/**
	 * The graphics.
	 */
	private static final Graphics GRAPHIC = new Graphics(3152);

	@Override
	public int attack(final QueenBlackDragon npc, final Player victim) {
		npc.setNextAnimation(ANIMATION);
		npc.setNextGraphics(GRAPHIC);
		victim.getPackets()
				.sendGameMessage(
						"<col=FFCC00>The Queen Black Dragon gathers her strength to breath extremely hot flames.</col>");
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				int hit = 1950;
				int distance = Utils.getDistance(
						npc.getBase().transform(33, 31, 0), victim);
				hit /= (distance / 3) + 1;
				victim.setNextAnimation(new Animation(Combat
						.getDefenceEmote(victim)));
				victim.applyHit(new Hit(npc, hit, HitLook.REGULAR_DAMAGE));
				if (++count == 3) {
					stop();
				}
			}
		}, 4, 1);
		return Utils.random(8, 15);
	}

	@Override
	public boolean canAttack(QueenBlackDragon npc, Player victim) {
		return true;
	}

}