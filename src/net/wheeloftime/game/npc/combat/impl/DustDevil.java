package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.slayer.Slayer;
import net.wheeloftime.utils.Utils;

public class DustDevil extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1624 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		if (!Utils.isOnRange(npc, target, 0))
			return 0;
		NPCCombatDefinitions def = npc.getCombatDefinitions();
		if (!Slayer.hasFaceMask(target)) {
			Player targetPlayer = (Player) target;
			int randomSkill = Utils.random(0, 6);
			int currentLevel = targetPlayer.getSkills().getLevel(randomSkill);
			targetPlayer.getSkills().set(randomSkill,
					currentLevel < 5 ? 0 : currentLevel - Utils.random(20));
			targetPlayer.getPackets().sendGameMessage(
					"The dust devil's smoke suffocates you.");
			delayHit(npc, 0, target,
					getMeleeHit(npc, targetPlayer.getMaxHitpoints() / 4));
		} else
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(npc,
							getMaxHit(npc, npc.getAttackStyle(), target)));
		npc.setNextAnimation(new Animation(def.getAttackEmote()));
		return npc.getAttackSpeed();
	}

}
