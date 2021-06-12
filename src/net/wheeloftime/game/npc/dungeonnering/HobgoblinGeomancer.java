package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class HobgoblinGeomancer extends DungeonBoss {

	public HobgoblinGeomancer(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference) {
		super(id, tile, manager, reference);
	}

	public void sendTeleport(final WorldTile tile, final RoomReference room) {
		setCantInteract(true);
		setNextAnimation(new Animation(12991, 70));
		setNextGraphics(new Graphics(1576, 70, 0));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				setCantInteract(false);
				setNextAnimation(new Animation(-1));
				setNextWorldTile(Utils.getFreeTile(getManager()
						.getRoomCenterTile(room), 6));
				resetReceivedHits();
			}
		}, 5);
	}
}
