package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.utils.Utils;

public class JadinkoCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 13820, 13821, 13822 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		boolean isDistanced = !Utils.isOnRange(npc, target, 0);
		if (target instanceof Player
				&& ((Player) target).getPrayer().isMeleeProtecting())
			isDistanced = true;
		if (isDistanced)
			magicAttack(npc, target);
		else
			meleeAttack(npc, target);
		return npc.getAttackSpeed();
	}

	private void magicAttack(NPC npc, Entity target) {
		npc.setNextAnimation(new Animation(npc.getId() == 13820 ? 3031 : 3215));
		delayHit(
				npc,
				2,
				target,
				getMagicHit(npc,
						getMaxHit(npc, NPCCombatDefinitions.MAGE, target)));
	}

	private void meleeAttack(NPC npc, Entity target) {
		npc.setNextAnimation(new Animation(npc.getId() == 13820 ? 3009 : 3214));
		delayHit(
				npc,
				0,
				target,
				getMeleeHit(npc,
						getMaxHit(npc, NPCCombatDefinitions.MELEE, target)));
	}
}