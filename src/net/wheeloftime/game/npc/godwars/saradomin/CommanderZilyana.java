package net.wheeloftime.game.npc.godwars.saradomin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.wheeloftime.executor.GameExecutorManager;
import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.TimersManager.RecordKey;
import net.wheeloftime.game.player.content.FriendsChat;
import net.wheeloftime.game.player.content.activities.minigames.GodWarsBosses;
import net.wheeloftime.game.player.controllers.Controller;
import net.wheeloftime.game.player.controllers.GodWars;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class CommanderZilyana extends NPC {

	public CommanderZilyana(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setIntelligentRouteFinder(true);
		setForceFollowClose(true);
		setLureDelay(6000);// approximately 6 seconds lure
		setDropRateFactor(3); // triples chances
	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
	@Override
	public void sendDeath(final Entity source) {
		if (source instanceof Player)
			((Player) source).getTimersManager().removeTimer(RecordKey.COMMANDER_ZILYANA);
		increaseKills(RecordKey.COMMANDER_ZILYANA, false);
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
					if (source instanceof Player) {
						Player player = (Player) source;
						List<Player> players = FriendsChat
								.getLootSharingPeople(player);
						if (players != null) {
							for (Player p : players) {
								if (p == null)
									continue;
								Controller controller = p.getControllerManager( )
										.getController();
								if (controller != null
										&& controller instanceof GodWars) {
									GodWars godController = (GodWars) controller;
									godController.incrementKillCount( 2 );
								}
							}
						}
					}
					giveXP();
					drop();
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

	@Override
	public void setRespawnTask() {
		if (!hasFinished()) {
			reset();
			setLocation(getRespawnTile());
			finish();
		}
		final NPC npc = this;
		GameExecutorManager.slowExecutor.schedule( ( ) -> {
			try {
				setFinished(false);
				World.addNPC(npc);
				npc.setLastRegionId(0);
				World.updateEntityRegion(npc);
				loadMapRegions();
				GodWarsBosses.respawnSaradominMinions();
			} catch (Error e) {
				e.printStackTrace( );
			}
		}, getCombatDefinitions().getRespawnDelay() * 600,
				TimeUnit.MILLISECONDS);
	}

}