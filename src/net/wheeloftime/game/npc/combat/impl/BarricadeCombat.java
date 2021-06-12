package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;

public class BarricadeCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Barricade" };
	}

	/**
	 * empty
	 */
	@Override
	public int attack(NPC npc, Entity target) {
		return 0;
	}
}
