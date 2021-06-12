package net.wheeloftime.game.player.content.activities.ports;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.map.MapBuilder;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

/**
 * 
 * @author Nathan
 */

public class PlayerPorts {

	private Player player;
	public int[] boundChuncks;
	public boolean insidePort;

	public void initalizePort() {
		if (!isInsidePort()) {
			player.getControllerManager().startController(
					"PlayerPortsController", this);
			boundChuncks = MapBuilder.findEmptyRegionBound(12, 14);
			MapBuilder.copyAllPlanesMap(1534, 911, boundChuncks[0],
					boundChuncks[1], 12, 14);
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.setNextWorldTile(new WorldTile(
							boundChuncks[0] * 0 + 96,
							boundChuncks[1] * 0 + 110, 0));
				}

			});
		}
	}

	public boolean isInsidePort() {
		return insidePort;
	}

	public boolean setInsidePort(boolean value) {
		return insidePort = value;
	}

	public int[] getBoundChuncks() {
		return boundChuncks;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}