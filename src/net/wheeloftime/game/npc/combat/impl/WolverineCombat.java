package net.wheeloftime.game.npc.combat.impl;

import java.util.Random;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.player.Player;

public class WolverineCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 14899 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final Player player = (Player) target;
		npc.setNextAnimation(new Animation(10961));
		int damage = player.getSkills().getCombatLevel() / 3
				+ new Random().nextInt(20) + 40;
		int dclaw1 = damage / 2;
		int dclaw2 = damage / 3;
		int dclaw3 = damage / 3;
		delayHit(npc, 2, target, getMeleeHit(npc, damage));
		delayHit(npc, 2, target, getMeleeHit(npc, dclaw1));
		delayHit(npc, 2, target, getMeleeHit(npc, dclaw2 / 10));
		delayHit(npc, 2, target, getMeleeHit(npc, dclaw3 / 10));
		return npc.getAttackSpeed();
	}
}
