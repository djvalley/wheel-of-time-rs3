package net.wheeloftime.game.player.controllers;

import net.wheeloftime.Settings;
import net.wheeloftime.game.Animation;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class JailController extends Controller {

	private int nextCheck;
	private long time;

	@Override
	public void start() {
		time = (long) this.getArguments()[0];
	}

	@Override
	public boolean logout() {
		setArguments(new Object[] { time });
		return false;
	}

	@Override
	public boolean login() {
		start();
		return false;
	}

	@Override
	public void process() {
		nextCheck++;
		if (nextCheck % 100 == 0) {
			time--;
			if (time <= 0)
				leaveJail(player, true);
		}
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		return false;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		return false;
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		player.getPackets().sendGameMessage(
				"You cannot do any activities while being jailed.");
		return false;
	}

	@Override
	public boolean sendDeath() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				player.stopAll();
				if (loop == 0) {
					player.setNextAnimation(player.getDeathAnimation());
				} else if (loop == 1) {
					player.getPackets().sendGameMessage(
							"Oh dear, you have died.");
				} else if (loop == 3) {
					player.setNextAnimation(new Animation(-1));
					player.reset();
				}
				loop++;
			}
		}, 0, 1);
		return true;
	}

	public static void leaveJail(Player player, boolean full) {
		if (full)
			player.getPackets().sendGameMessage(
					"You have served your time and may return back to "
							+ Settings.SERVER_NAME + ".");
		else
			player.getPackets().sendGameMessage(
					"Your punishment has been removed early.");
		player.setNextWorldTile(Settings.HOME_LOCATION);
		player.getControllerManager().removeControllerWithoutCheck();
	}

	public static void imprison(Player player, long time) {
		player.stopAll();
		player.lock(4);
		player.getControllerManager().startController("JailController", time);
	}
}