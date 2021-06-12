package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.*;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Logger;
import net.wheeloftime.utils.Utils;

public class EvilChickenCombat extends CombatScript {

	/**
	 * @author: miles M
	 */

	private int cycle;

	@Override
	public Object[] getKeys() {
		return new Object[] { "Evil Chicken" };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		if (target.getX() == npc.getX() && target.getY() == npc.getY())
			target.applyHit(new Hit(target, Utils.random(1000, 2000),
					HitLook.POISON_DAMAGE));
		switch (Utils.random(5)) {
		case 0:
			// ((Player) target).setNextWorldTile(getSpawn());
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					npc.setNextWorldTile(new WorldTile(
							((Player) target).getX(), ((Player) target).getY(),
							((Player) target).getPlane()));
				}
			}, 1);
			npc.setHitpoints(npc.getHitpoints() + 5000);
			npc.setNextForceTalk(new ForceTalk("I'm getting stronger!"));
			break;
		case 2:
			if (target instanceof Player)
				((Player) target).getPrayer().drain(250);
			npc.setNextForceTalk(new ForceTalk("Bwaaaauuuuk bwuk bwuk"));
			target.applyHit(new Hit(target, Utils.random(1000, 3000),
					HitLook.REGULAR_DAMAGE));
			break;
		}
		target.setNextGraphics(new Graphics(3701));
		target.applyHit(new Hit(target, Utils.random(2000, 4000),
				HitLook.MAGIC_DAMAGE));
		if (cycle == 4)
			specialAttack(npc);
		cycle++;
		delayHit(
				npc,
				0,
				target,
				getMagicHit(npc,
						getMaxHit(npc, NPCCombatDefinitions.MAGE, target)));
		return npc.getAttackSpeed();
	}

	private void specialAttack(NPC npc) {
		cycle = 0;
		Logger.log("EvilChickenCombat", "specialAttack");
		npc.setNextForceTalk(new ForceTalk("DIE HUMAN!"));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				for (Entity t : npc.getPossibleTargets()) {
					t.applyHit(new Hit(npc, 4000 + Utils.random(2680),
							HitLook.REGULAR_DAMAGE));
					t.setNextForceTalk(new ForceTalk("OUCH!!!!!!!"));
					t.setNextAnimation(new Animation(14388));
					t.setNextGraphics(new Graphics(3019));
				}
			}
		}, 1);
	}
}