package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.utils.Utils;

public class MaxCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 15976, 15977, 15978 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		int attackStyle = npc.getId() - 15976;
		switch (attackStyle) {
		case 0:
			// 6000 maxhit
			npc.setNextAnimation(new Animation(24002));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(
							npc,
							getMaxHit(npc, 6000, NPCCombatDefinitions.MELEE,
									target)));
			break;
		case 1:
			// 6000 maxhit
			npc.setNextAnimation(new Animation(23943));
			int delay = Utils.projectileTimeToCycles(World.sendProjectileNew(
					npc, target, 16, 40, 41, 55, 5, Utils.random(5), 5)
					.getEndTime()) - 1;
			delayHit(
					npc,
					delay,
					target,
					getRangedHit(
							npc,
							getMaxHit(npc, 6000, NPCCombatDefinitions.RANGE,
									target)));
			break;
		case 2:
			// 6000 maxhit
			// 30, 30, 90
			npc.setNextAnimation(new Animation(14223));
			int projectileTime = World.sendProjectileNew(npc, target, 2735, 30,
					30, 51, 5, 0, 90).getEndTime();
			delay = Utils.projectileTimeToCycles(projectileTime) - 1;
			World.sendProjectileNew(npc, target, 2736, 30, 30, 51, 5, 20, 90)
					.getEndTime();
			World.sendProjectileNew(npc, target, 2736, 30, 30, 51, 5, 110, 90)
					.getEndTime();
			target.setNextGraphics(new Graphics(2741, projectileTime, 96));
			delayHit(
					npc,
					delay,
					target,
					getMagicHit(
							npc,
							getMaxHit(npc, 6000, NPCCombatDefinitions.MAGE,
									target)));
			break;
		}
		return 5;
	}

}
