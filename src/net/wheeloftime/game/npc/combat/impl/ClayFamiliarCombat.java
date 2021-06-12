package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;

public class ClayFamiliarCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 8241, 8243, 8245, 8247, 8249 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions def = npc.getCombatDefinitions();
		if (target instanceof Player) {
			Player player = (Player) target;
			if (player.getAppearence().isNPC()) {
				npc.getCombat().removeTarget();
				return npc.getAttackSpeed();
			}
		}
		delayHit(npc, 0, target,
				getMeleeHit(npc, getMaxHit(npc, npc.getAttackStyle(), target)));
		npc.setNextAnimation(new Animation(def.getAttackEmote()));
		return npc.getAttackSpeed();
	}
}
