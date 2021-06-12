package net.wheeloftime.game.npc.combat.impl.godwars2;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Projectile;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.godwars2.gregorovic.Shadow;
import net.wheeloftime.utils.Utils;

public class ShadowCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 22444 };
	}

	public void shurikenAttack(NPC npc, Entity target) {
		Shadow shadow = (Shadow) npc;
		shadow.setNextAnimation(new Animation(28228));
		Projectile projectile = World.sendProjectileNew(npc, target, 6133, 40, 30, 125, 1, 0, 0);
		delayHit(npc, Utils.projectileTimeToCycles(projectile.getEndTime()) - 1, target,
				getRangedHit(npc, getMaxHit(npc, Utils.random(500, 816), NPCCombatDefinitions.RANGE, target)));
	}

	@Override
	public int attack(NPC npc, Entity target) {
		Shadow shadow = (Shadow) npc;
		switch (shadow.getPhase()) {
		case 0:
			shurikenAttack(npc, target);
			break;
		}
		shadow.nextPhase();
		if (shadow.getPhase() < 0 || shadow.getPhase() > 0)
			shadow.setPhase(0);
		return npc.getAttackSpeed();
	}

}