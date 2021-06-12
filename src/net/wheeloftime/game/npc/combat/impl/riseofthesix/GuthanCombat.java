package net.wheeloftime.game.npc.combat.impl.riseofthesix;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.riseofthesix.Guthan;

public class GuthanCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 18541 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final Guthan boss = (Guthan) npc;
		int damage = getMaxHit(npc, 3500, NPCCombatDefinitions.MELEE, target);
		npc.setNextAnimation(new Animation(npc.getCombatDefinitions()
				.getAttackEmote()));
		delayHit(npc, 0, target, getMeleeHit(npc, damage));
		return 7;
	}

}