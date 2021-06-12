package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.utils.Utils;

public class SpiritualGaurdianCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 10700 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		npc.setNextAnimation(new Animation(Utils.random(2) == 0 ? 13036 : 13035));
		delayHit(
				npc,
				0,
				target,
				getMeleeHit(npc,
						getMaxHit(npc, NPCCombatDefinitions.MELEE, target)));
		return npc.getAttackSpeed();
	}
}
