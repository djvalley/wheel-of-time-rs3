package net.wheeloftime.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.Random;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.others.Tertius;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

/**
 * @author Nosz
 * 
 * 
 * 
 */
public class TertiusCombat extends CombatScript {
	public float damageBuff;
	int Stage;

	@Override
	public Object[] getKeys() {
		return new Object[] { 17151 };
	}

	public void spawnFollowers(final NPC npc, final Player p) {
		if (npc.getHitpoints() > (npc.getMaxHitpoints() / 4) * 3) { // 100%
			p.getTemporaryAttributtes().put("TertiusStage", 0);
		}
		if (npc.getHitpoints() <= (npc.getMaxHitpoints() / 4) * 3
				&& (int) p.getTemporaryAttributtes().get("TertiusStage") == 0) { // 75%
			p.setForceMultiArea(true);
			p.isForceMultiArea();
			Tertius tertius = (Tertius) npc;
			tertius.spawnSpidersOne(p);
			p.getTemporaryAttributtes().put("TertiusStage", 1);
		}
		if (npc.getHitpoints() <= (npc.getMaxHitpoints() / 4) * 2
				&& (int) p.getTemporaryAttributtes().get("TertiusStage") == 1) { // 50%
			Tertius tertius = (Tertius) npc;
			tertius.spawnSpidersTwo(p);
			p.getTemporaryAttributtes().put("TertiusStage", 2);
		}
		if (npc.getHitpoints() <= (npc.getMaxHitpoints() / 4)
				&& (int) p.getTemporaryAttributtes().get("TertiusStage") == 2) { // 25%
			Tertius tertius = (Tertius) npc;
			tertius.spawnSpidersThree(p);
			p.getTemporaryAttributtes().put("TertiusStage", 3);
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
		if (npc.getPossibleTargets().size() != 0) {
			for (final Entity t : possibleTargets) {
				if (t instanceof Player) {
					final Player p = (Player) t;
					if (npc.withinDistance(npc, 15)) {
						npc.heal(300);
					}
					spawnFollowers(npc, p);
					WorldTasksManager.schedule(new WorldTask() {// NORMAL ATTACK
								Random rn = new Random();
								int max = 1000;
								int min = 750;
								int range = max - min + 1;
								int randomNum = rn.nextInt(range) + min;
								private int gameTick;

								@Override
								public void run() {
									gameTick++;
									if (gameTick == 1) {
										npc.setNextAnimation(new Animation(
												20277));
										npc.setNextGraphics(new Graphics(3975));
									}
									if (gameTick == 2) {
										World.sendProjectile(npc, t, 3978, 10,
												6, 40, 5, 0, 0);
										p.applyHit(new Hit(p, randomNum,
												HitLook.MAGIC_DAMAGE, 0));

									}
								}

							}, 0, 0);

					WorldTasksManager.schedule(new WorldTask() { // AOE SPELL
								int x;
								int y;;
								int max = 3000;
								int min = 1000;
								Random rn = new Random();
								int range = max - min + 1;
								int randomNum = rn.nextInt(range) + min;
								private int gameTick;

								@Override
								public void run() {
									gameTick++;
									if (gameTick == 1) {
										npc.setNextAnimation(new Animation(
												20277));
										x = p.getX();
										y = p.getY();

									}
									if (gameTick == 4) {
										World.sendGraphics(null, new Graphics(
												3974), new WorldTile(x, y, 1));
									}
									if (gameTick == 5) {
										if (target.getX() == x
												&& target.getY() == y) {
											p.applyHit(new Hit(p, randomNum,
													HitLook.MAGIC_DAMAGE, 0));
										}

									}
								}

							}, 0, 0);
				}
			}
		}

		return npc.getAttackSpeed();
	}
}
