package net.wheeloftime.game.player.controllers.bossInstance;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class KalphiteKingInstanceController extends BossInstanceController {

	@Override
	public boolean processObjectClick5(final WorldObject object) {
		if (object.getId() == 82016) {
			player.lock();
			player.setNextAnimation(new Animation(19499));
			WorldTasksManager.schedule(new WorldTask() { // to remove at same
															// time it teleports
						@Override
						public void run() {
							player.setNextAnimation(new Animation(-1));
							getInstance().leaveInstance(player,
									BossInstance.EXITED);
							removeController();
						}
					}, 3);
			return false;
		}
		return true;
	}
}
