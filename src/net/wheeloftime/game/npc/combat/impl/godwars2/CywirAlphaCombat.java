package net.wheeloftime.game.npc.combat.impl.godwars2;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.godwars2.helwyr.CywirAlpha;
import net.wheeloftime.utils.Utils;

public class CywirAlphaCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 22439 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		CywirAlpha wolf = (CywirAlpha) npc;
		wolf.setNextAnimation(new Animation(23578));
		delayHit(
				npc,
				0,
				target,
				getMeleeHit(
						npc,
						getMaxHit(npc, Utils.random(200, 792),
								NPCCombatDefinitions.MELEE, target)));
		return npc.getAttackSpeed();
	}

}