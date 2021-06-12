package net.wheeloftime.game.player.controllers.trollinvasion;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;

public class TrollWizard extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 12435 };

	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int hit = 0;
		npc.setNextAnimation(new Animation(1938));
		hit = getMaxHit(npc, 300, NPCCombatDefinitions.MELEE, target);
		delayHit(npc, 2, target, getMeleeHit(npc, hit));
		return defs.getAttackGfx();
	}

}
