package net.wheeloftime.game.npc.combat.impl.godwars2;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Projectile;
import net.wheeloftime.game.World;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.godwars2.twinfuries.Nymora;
import net.wheeloftime.utils.Utils;

public class NymoraCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 22456 };
	}

	public void rangedAttack(NPC npc, Entity target) {
		Nymora nymora = (Nymora) npc;
		nymora.setNextAnimation(new Animation(28250));
		Projectile projectile = World.sendProjectileNew(npc, target, 6136, 40,
				30, 125, 1.4, 0, 0);
		delayHit(
				npc,
				Utils.projectileTimeToCycles(projectile.getEndTime()) - 1,
				target,
				getRangedHit(
						npc,
						getMaxHit(npc, Utils.random(500, 816),
								NPCCombatDefinitions.RANGE, target)));
	}

	@Override
	public int attack(NPC npc, Entity target) {
		Nymora nymora = (Nymora) npc;
		switch (nymora.getPhase()) {
		case 0:
			rangedAttack(npc, target);
			break;
		}
		nymora.nextPhase();
		if (nymora.getPhase() < 0 || nymora.getPhase() > 0)
			nymora.setPhase(0);
		return npc.getAttackSpeed();
	}

}
