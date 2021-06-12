package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.utils.Utils;

public class SwarmCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 411 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		delayHit(npc, 0, target, getRegularHit(npc, Utils.random(100)));
		return 6;
	}
}
