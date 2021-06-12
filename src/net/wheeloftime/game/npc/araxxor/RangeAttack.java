package net.wheeloftime.game.npc.araxxor;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.World;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.Combat;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class RangeAttack implements AraxxorAttack {

	@Override
	public int attack(final Araxxor npc, final Player p) {
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				p.AraxxorAttackCount++;
				stop();
				int hit = 0;
				World.sendProjectile(npc, p, 4990, 41, 16, 21, 0, 16, -20);
				hit = Utils.random(0 + Utils.random(1000), 3000);
				npc.setNextAnimation(new Animation(24047));
				p.setNextAnimation(new Animation(Combat.getDefenceEmote(p)));

				p.applyHit(new Hit(npc, hit, hit == 0 ? HitLook.MISSED
						: HitLook.RANGE_DAMAGE));
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
