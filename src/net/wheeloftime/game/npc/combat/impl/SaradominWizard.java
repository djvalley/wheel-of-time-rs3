package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.EffectsManager;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class SaradominWizard extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1264 };
	}

	@Override
	public int attack(NPC npc, final Entity target) {
		int style = Utils.random(!Utils.isOnRange(npc, target, 0) ? 1 : 0, 3);
		switch (style) {
		case 0:
			npc.setNextAnimation(new Animation(376));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(npc,
							getMaxHit(npc, NPCCombatDefinitions.MELEE, target)));
			if (Utils.random(3) == 0)
				EffectsManager.makePoisoned(target, 80);
			break;
		case 1:
		case 2:
			final int damage = getMaxHit(npc, NPCCombatDefinitions.MAGE, target);
			npc.setNextAnimation(new Animation(811));
			delayHit(npc, 2, target, getMagicHit(npc, damage));
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					if (damage > 0)
						target.setNextGraphics(new Graphics(98));
					else
						target.setNextGraphics(new Graphics(76));
				}
			});
			break;
		}
		return npc.getAttackSpeed();
	}
}
