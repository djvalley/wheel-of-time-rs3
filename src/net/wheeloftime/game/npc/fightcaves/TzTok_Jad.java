package net.wheeloftime.game.npc.fightcaves;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.TimersManager.RecordKey;
import net.wheeloftime.game.player.content.activities.minigames.FightCaves;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class TzTok_Jad extends FightCavesNPC {

	private boolean spawnedMinions;
	private FightCaves Controller;

	public TzTok_Jad(int id, WorldTile tile, FightCaves Controller) {
		super(id, tile);
		this.Controller = Controller;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0;
	}

	@Override
	public boolean isStunImmune() {
		return true; // as not setted imune yet it is
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (!spawnedMinions && getHitpoints() < getMaxHitpoints() / 2) {
			spawnedMinions = true;
			Controller.spawnHealers(this);
		}
	}

	@Override
	public void sendDeath(Entity source) {
		if (source instanceof Player)
			((Player) source).getTimersManager().removeTimer(
					RecordKey.FIGHT_CAVES);
		increaseKills(RecordKey.FIGHT_CAVES, false);
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
					setNextGraphics(new Graphics(2924 + getSize()));
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					finish();
					Controller.win();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

}
