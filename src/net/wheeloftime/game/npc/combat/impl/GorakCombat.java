package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;

public class GorakCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Gorak" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int damage = getMaxHit(npc, NPCCombatDefinitions.MELEE, target);
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		delayHit(npc, 0, target, new Hit(npc, damage, HitLook.REGULAR_DAMAGE));
		return npc.getAttackSpeed();
	}
}
