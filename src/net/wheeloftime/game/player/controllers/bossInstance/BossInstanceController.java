package net.wheeloftime.game.player.controllers.bossInstance;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.map.bossInstance.BossInstanceHandler;
import net.wheeloftime.game.map.bossInstance.BossInstanceHandler.Boss;
import net.wheeloftime.game.npc.others.GraveStone;
import net.wheeloftime.game.player.MusicsManager;
import net.wheeloftime.game.player.controllers.Controller;
import net.wheeloftime.game.player.controllers.events.DeathEvent;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class BossInstanceController extends Controller {

	private BossInstance instance;

	@Override
	public void start() {

		instance = (BossInstance) getArguments()[0]; // cant save this as not
		// serializable would null
		// :L
		getArguments()[0] = instance.getBoss(); // gotta save reference for
		// which instance im at
	}

	@Override
	public void process() {

		if (instance != null && Utils.currentWorldCycle() % 120 == 0)
			instance.playMusic(player);
	}

	@Override
	public boolean login() {
		// shouldnt happen but better be safe
		if (getArguments() == null || getArguments().length == 0)
			return true;
		Boss boss = (Boss) getArguments()[0];
		instance = BossInstanceHandler.joinInstance(player, boss, "", true);
		return instance == null; // if failed. remove
	}

	public BossInstance getInstance() {

		return instance;
	}

	@Override
	public boolean sendDeath() {

		player.lock(8);
		player.stopAll();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {

				if (loop == 0) {
					player.setNextAnimation(player.getDeathAnimation());
				} else if (loop == 1) {
					player.getPackets().sendGameMessage(
							"Oh dear, you have died.");
				} else if (loop == 3) {
					instance.leaveInstance(player, BossInstance.DIED);
					removeController();
					if (instance.getSettings().isPracticeMode()) {
						player.reset();
						player.setNextWorldTile(instance.getBoss()
								.getOutsideTile());
					} else {
						WorldTile graveStoneLoc = instance.getBoss()
								.getGraveStoneTile();
						if (graveStoneLoc == null)
							graveStoneLoc = instance.isPublic() ? new WorldTile(
									player) : instance.getBoss()
									.getOutsideTile();
						if (instance.getBoss() == Boss.Corporeal_Beast) { // drop
							// items
							// instead
							player.reset();
							Integer[][] slots = GraveStone
									.getItemSlotsKeptOnDeath(player, true,
											player.hasSkull(), player
													.getPrayer()
													.isProtectingItem());
							WorldTile respawnTile = DeathEvent
									.getRespawnHub(player);
							player.sendItemsOnDeath(null, graveStoneLoc,
									respawnTile, true, slots, true);
							player.setNextWorldTile(respawnTile);
						} else
							player.getControllerManager().startController(
									"DeathEvent", graveStoneLoc,
									player.hasSkull());
					}
				} else if (loop == 4) {
					player.getMusicsManager().playMusicEffect(
							MusicsManager.DEATH_MUSIC_EFFECT);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public boolean logout() {

		instance.leaveInstance(player, BossInstance.LOGGED_OUT);
		if (!instance.isPublic())
			removeController();
		return false;
	}

	@Override
	public void forceClose() {
		if (instance != null)
			instance.leaveInstance(player, BossInstance.EXITED);
	}

	@Override
	public void magicTeleported(int type) {

		instance.leaveInstance(player, BossInstance.TELEPORTED);
		removeController();
	}
}