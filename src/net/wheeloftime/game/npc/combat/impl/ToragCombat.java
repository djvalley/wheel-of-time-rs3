package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.utils.Utils;

public class ToragCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2029 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		int damage = getMaxHit(npc, NPCCombatDefinitions.MELEE, target);
		if (damage != 0 && target instanceof Player && Utils.random(3) == 0) {
			target.setNextGraphics(new Graphics(399));
			Player targetPlayer = (Player) target;
			targetPlayer
					.setRunEnergy(targetPlayer.getRunEnergy() > 4 ? targetPlayer
							.getRunEnergy() - 4 : 0);
		}
		delayHit(npc, 0, target, getMeleeHit(npc, damage));
		return npc.getAttackSpeed();
	}
}
