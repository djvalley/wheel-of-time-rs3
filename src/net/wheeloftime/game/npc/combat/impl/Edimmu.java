package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Projectile;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class Edimmu extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 10704 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		boolean melee = Utils.random(2) == 0;
		int damage = getMaxHit(npc, melee ? NPCCombatDefinitions.MELEE
				: NPCCombatDefinitions.MAGE, target);

		if (!Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(),
				target.getX(), target.getY(), target.getSize(), 0))
			sendMagicAttack(npc, target, damage);
		else {
			if (melee) {
				if (Utils.random(2) == 0) {
					delayHit(npc, 0, target, getMeleeHit(npc, damage));
					delayHit(npc, 1, target, getMeleeHit(npc, damage / 2));
					delayHit(npc, 2, target, getMeleeHit(npc, damage / 4));
					npc.setNextAnimation(new Animation(13735));
				} else {
					npc.setNextAnimation(new Animation(13734));
					delayHit(npc, 0, target, getMeleeHit(npc, damage));
				}
			} else
				sendMagicAttack(npc, target, damage);
		}
		return 5;
	}

	private void sendMagicAttack(final NPC npc, Entity target, final int damage) {
		npc.setNextAnimation(new Animation(13734));
		Projectile projectile = World.sendProjectileNew(npc, target, 2615, 41,
				16, 35, 4, 10, 0);
		int endTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		delayHit(npc, endTime, target, getMagicHit(npc, damage));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				npc.heal(damage);
			}
		}, endTime);
	}
}
