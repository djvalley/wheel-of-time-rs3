package net.wheeloftime.game.player.controllers.trollinvasion;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;

public class Dynamite extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Dynamite" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int hit = 0;
		hit = getMaxHit(npc, 200, NPCCombatDefinitions.MAGE, target);

		delayHit(npc, 2, target, getMagicHit(npc, hit));

		return defs.getAttackGfx();
	}
}