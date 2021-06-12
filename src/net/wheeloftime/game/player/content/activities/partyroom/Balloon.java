package net.wheeloftime.game.player.content.activities.partyroom;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

/**
 * @author Miles Black (bobismyname)
 * @date Nov 2, 2016
 */

public class Balloon extends WorldObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6014958764470013424L;
	private Item item;
	private boolean popped = false;

	public Balloon(int id, int type, int rotation, int x, int y, int plane) {
		super(id, type, rotation, x, y, plane);
	}

	public void handlePop(final Player player) {
		if (!popped) {
			if (player.isAnIronMan())
				return;
			player.setNextAnimation(new Animation(794));
			popped = true;
			player.lock();
			World.removeObject(this);
			final WorldObject poppedBalloon = new WorldObject(getId() + 8, 10,
					this.getRotation(), getX(), getY(), getPlane());
			World.spawnObject(poppedBalloon);

			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					if (item != null)
						World.addGroundItem(item, new WorldTile(getX(), getY(),
								getPlane()), player, true, 60, 2);
					World.removeObject(poppedBalloon);
					player.unlock();
				}
			}, 1);
		}
	}

	public Balloon setItem(Item item) {
		this.item = item;
		return this;
	}

}