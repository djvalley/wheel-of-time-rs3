package net.wheeloftime.game.npc.combat.impl.riseofthesix;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Projectile;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.riseofthesix.Ahrim;

public class AhrimCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 18538, 18539 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final Ahrim boss = (Ahrim) npc;
		Projectile projectile = World.sendProjectileNew(boss, target, 559, 5,
				10, 30, 0.9, 0, 0);
		int damage = getMaxHit(boss, 3000, NPCCombatDefinitions.MAGE, target);
		delayHit(npc, 3, target, getMagicHit(npc, damage));
		target.setNextGraphics(new Graphics(377));
		return 5;
	}

}