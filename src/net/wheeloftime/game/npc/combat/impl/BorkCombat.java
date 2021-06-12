package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.others.Bork;

public class BorkCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Bork" };
	}

	@Override
	public int attack(NPC npc, Entity target) {

		final NPCCombatDefinitions combatDefinitions = npc
				.getCombatDefinitions();
		Bork bork = (Bork) npc;
		if (npc.getHitpoints() <= (combatDefinitions.getHitpoints() * 0.6)
				&& !bork.isSpawnedMinions()) {
			bork.spawnMinions();
			return 0;
		}
		npc.setNextAnimation(new Animation(combatDefinitions.getAttackEmote()));
		delayHit(
				npc,
				0,
				target,
				getMeleeHit(npc,
						getMaxHit(npc, NPCCombatDefinitions.MELEE, target)));
		return npc.getAttackSpeed();
	}
}