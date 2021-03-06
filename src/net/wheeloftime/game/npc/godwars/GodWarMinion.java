package net.wheeloftime.game.npc.godwars;

import java.util.List;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.FriendsChat;
import net.wheeloftime.game.player.controllers.Controller;
import net.wheeloftime.game.player.controllers.GodWars;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class GodWarMinion extends NPC {

	private int type, ticks = 10;

	public GodWarMinion(int id, int type, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		this.type = type;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (getCombat().underCombat()) {
			if (ticks-- == 0) {
				ticks = 10;
				getCombat().removeTarget();
			}
		}
	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
	@Override
	public void sendDeath(final Entity source) {
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
				} else if (loop >= defs.getDeathDelay()) {
					drop();
					if (source instanceof Player) {
						Player player = (Player) source;
						List<Player> players = FriendsChat
								.getLootSharingPeople(player);
						if (players != null) {
							for (Player p : players) {
								if (p == null)
									continue;
								Controller Controller = p
										.getControllerManager().getController();
								if (Controller != null
										&& Controller instanceof GodWars) {
									GodWars godController = (GodWars) Controller;
									godController.incrementKillCount(type);
								}
							}
						}
					}
					giveXP();
					reset();
					setLocation(getRespawnTile());
					finish();
					setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	public void respawn() {
		setFinished(false);
		World.addNPC(this);
		setLastRegionId(0);
		World.updateEntityRegion(this);
		loadMapRegions();
	}
}