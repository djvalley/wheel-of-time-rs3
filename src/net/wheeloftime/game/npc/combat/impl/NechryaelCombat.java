package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;

public class NechryaelCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Nechryael" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		npc.setNextAnimation(new Animation(npc.getCombatDefinitions()
				.getAttackEmote()));
		delayHit(npc, 0, target,
				getMagicHit(npc, getMaxHit(npc, npc.getAttackStyle(), target)));
		return npc.getAttackSpeed();
	}
}