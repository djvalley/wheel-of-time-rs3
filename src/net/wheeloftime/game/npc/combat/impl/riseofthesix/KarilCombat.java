package net.wheeloftime.game.npc.combat.impl.riseofthesix;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Projectile;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.riseofthesix.Karil;

public class KarilCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 18543 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final Karil boss = (Karil) npc;
		Projectile projectile = World.sendProjectileNew(boss, target, 955, 5,
				10, 30, 0.9, 0, 0);
		int damage = getMaxHit(boss, 3000, NPCCombatDefinitions.RANGE, target);
		npc.setNextAnimation(new Animation(18232));
		delayHit(npc, 3, target, getRangedHit(npc, damage));
		return 7;
	}

}