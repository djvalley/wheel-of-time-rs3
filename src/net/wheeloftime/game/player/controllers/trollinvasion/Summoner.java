package net.wheeloftime.game.player.controllers.trollinvasion;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;

public class Summoner extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Summoner" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int hit = 0;
		hit = getMaxHit(npc, 250, NPCCombatDefinitions.MAGE, target);

		delayHit(npc, 2, target, getRangedHit(npc, hit));

		return defs.getAttackGfx();
	}

}
