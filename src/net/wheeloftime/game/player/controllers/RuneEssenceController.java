package net.wheeloftime.game.player.controllers;

import net.wheeloftime.game.ForceTalk;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class RuneEssenceController extends Controller {

	private static final WorldTile[] ESSENCE_COORDS = new WorldTile[] {
			new WorldTile(2911, 4832, 0), new WorldTile(2924, 4818, 0),
			new WorldTile(2900, 4818, 0), new WorldTile(2900, 4843, 0),
			new WorldTile(2922, 4844, 0) };

	public static void teleport(final Player player, NPC npc) {

		player.lock();
		npc.setNextForceTalk(new ForceTalk("Seventior disthiae molenko!"));
		npc.setNextGraphics(new Graphics(108));
		player.setNextGraphics(new Graphics(110));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {

				player.getControllerManager().startController(
						"RuneEssenceController", new WorldTile(player));
				player.useStairs(-1,
						ESSENCE_COORDS[Utils.random(ESSENCE_COORDS.length)], 0,
						1);
			}
		}, 2);
	}

	@Override
	public void start() {

	}

	@Override
	public boolean logout() {

		return false;
	}

	@Override
	public boolean login() {

		return false;
	}

	@Override
	public boolean sendDeath() {
		removeController();
		return true;
	}

	@Override
	public void magicTeleported(int type) {
		removeController();
	}

	/**
	 * return process normally
	 */
	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (object.getId() == 2503) {
			player.lock();
			player.setNextGraphics(new Graphics(110));
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					player.useStairs(-1, (WorldTile) getArguments()[0], 0, 1);
					removeController();
				}
			}, 2);
			return false;
		}
		return true;
	}

}
