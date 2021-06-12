package net.wheeloftime.game.player.controllers.trollinvasion;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.ForceTalk;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.utils.Utils;

public class PoorlyCookedKarambwan extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Poorly cooked Karambwan", 13669 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceAgressive(true);
		int hit = 0;
		int attackStyle = Utils.random(2);
		switch (attackStyle) {
		case 0:
			target.setNextForceTalk(new ForceTalk("*cough*"));
			npc.setNextForceTalk(new ForceTalk("*erk*"));
			hit = getMaxHit(npc, 200, NPCCombatDefinitions.MELEE, target);
			delayHit(npc, 1, target, getMeleeHit(npc, hit));
			break;
		case 1:
			hit = getMaxHit(npc, 200, NPCCombatDefinitions.MELEE, target);
			delayHit(npc, 1, target, getMeleeHit(npc, hit));
			break;
		}
		return defs.getAttackGfx();
	}

}
