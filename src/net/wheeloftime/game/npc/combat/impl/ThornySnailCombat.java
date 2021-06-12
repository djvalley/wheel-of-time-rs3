package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.familiar.Familiar;

public class ThornySnailCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 6807, 6806 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		npc.getCombatDefinitions();
		Familiar familiar = (Familiar) npc;
		boolean usingSpecial = familiar.hasSpecialOn();
		if (usingSpecial) {// priority over regular attack
			npc.setNextAnimation(new Animation(8148));
			npc.setNextGraphics(new Graphics(1385));
			World.sendProjectile(npc, target, 1386, 34, 16, 30, 35, 16, 0);
			delayHit(
					npc,
					1,
					target,
					getRangedHit(
							npc,
							getMaxHit(npc, 80, NPCCombatDefinitions.RANGE,
									target)));
			npc.setNextGraphics(new Graphics(1387));
		} else {
			npc.setNextAnimation(new Animation(8143));
			delayHit(
					npc,
					1,
					target,
					getRangedHit(
							npc,
							getMaxHit(npc, 40, NPCCombatDefinitions.RANGE,
									target)));
		}
		return npc.getAttackSpeed();
	}

}
