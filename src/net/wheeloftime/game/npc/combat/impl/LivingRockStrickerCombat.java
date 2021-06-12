package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.utils.Utils;

public class LivingRockStrickerCombat extends CombatScript {

	@Override
	public Object[] getKeys() {

		return new Object[] { 8833 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (!Utils.isOnRange(target, npc, 0)) {
			// TODO add projectile
			npc.setNextAnimation(new Animation(12196));
			delayHit(
					npc,
					1,
					target,
					getRangedHit(npc,
							getMaxHit(npc, NPCCombatDefinitions.RANGE, target)));
		} else {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(
							npc,
							getMaxHit(npc, 84, NPCCombatDefinitions.MELEE,
									target)));
			return npc.getAttackSpeed();
		}
		return npc.getAttackSpeed();
	}
}
