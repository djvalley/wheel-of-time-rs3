package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.utils.Utils;

public class CatableponCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 4397, 4398, 4399 };
	}

	@Override
	public int attack(NPC npc, final Entity target) {
		NPCCombatDefinitions def = npc.getCombatDefinitions();
		if (Utils.random(10) == 0 && target instanceof Player) {
			Player playerTarget = (Player) target;
			int strLvl = playerTarget.getSkills()
					.getLevel(Skills.STRENGTH);
			if (strLvl - playerTarget.getSkills().getLevel(Skills.STRENGTH) < 8) {
				playerTarget.getSkills().drainLevel(Skills.STRENGTH,
						(int) (strLvl * 0.15));
				npc.setNextAnimation(new Animation(4272));
				delayHit(
						npc,
						1,
						target,
						getMagicHit(
								npc,
								getMaxHit(npc, NPCCombatDefinitions.MAGE,
										target)));
				return npc.getAttackSpeed();
			}

		}
		delayHit(npc, 0, target,
				getMeleeHit(npc, getMaxHit(npc, npc.getAttackStyle(), target)));
		npc.setNextAnimation(new Animation(def.getAttackEmote()));
		return npc.getAttackSpeed();
	}
}
