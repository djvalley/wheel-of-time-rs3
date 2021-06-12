package net.wheeloftime.game.npc.combat.impl.dung;

import java.util.ArrayList;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.dungeonnering.AsteaFrostweb;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class AsteaFrostwebCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Astea Frostweb" };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(10) == 0) {
			AsteaFrostweb boss = (AsteaFrostweb) npc;
			boss.spawnSpider();
		}
		if (Utils.random(10) == 0) { // spikes
			ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			for (Entity t : possibleTargets)
				delayHit(
						npc,
						1,
						t,
						new Hit(
								npc,
								Utils.random((int) (npc
										.getMaxHit(NPCCombatDefinitions.MAGE) * 0.5) + 1),
								HitLook.REGULAR_DAMAGE));
			return npc.getAttackSpeed();
		} else {
			int attackStyle = Utils.random(2);
			if (attackStyle == 1) { // check melee
				if (Utils.getDistance(npc.getX(), npc.getY(), target.getX(),
						target.getY()) > 1)
					attackStyle = 0; // set mage
				else { // melee
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(
							npc,
							0,
							target,
							getMeleeHit(
									npc,
									getMaxHit(npc, NPCCombatDefinitions.MELEE,
											target)));
					return npc.getAttackSpeed();
				}
			}
			if (attackStyle == 0) { // mage
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				ArrayList<Entity> possibleTargets = npc.getPossibleTargets();

				int d = getMaxHit(npc, NPCCombatDefinitions.MAGE, target);
				delayHit(npc, 1, target, getMagicHit(npc, d));
				if (d != 0) {
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							if (target.isBound())
								target.setNextGraphics(new Graphics(1677, 0,
										100));
							else {
								target.setNextGraphics(new Graphics(369));
								target.setBoundDelay(8, true);
							}
						}
					}, 1);
					for (final Entity t : possibleTargets) {
						if (t != target && t.withinDistance(target, 2)) {
							int damage = getMaxHit(npc,
									NPCCombatDefinitions.MAGE, t);
							delayHit(npc, 1, t, getMagicHit(npc, damage));
							if (damage != 0) {
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										if (t.isBound())
											t.setNextGraphics(new Graphics(
													1677, 0, 100));
										else {
											t.setNextGraphics(new Graphics(369));
											t.setBoundDelay(8, true);
										}
									}
								}, 1);
							}

						}
					}
				}
				if (Utils.getDistance(npc.getX(), npc.getY(), target.getX(),
						target.getY()) <= 1) { // lure
					// after
					// freeze
					npc.resetWalkSteps();
					npc.addWalkSteps(target.getX() + Utils.random(3),
							target.getY() + Utils.random(3));
				}
			}
		}
		return npc.getAttackSpeed();
	}
}
