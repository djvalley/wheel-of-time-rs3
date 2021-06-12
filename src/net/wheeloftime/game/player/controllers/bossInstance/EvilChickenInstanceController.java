package net.wheeloftime.game.player.controllers.bossInstance;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.map.bossInstance.BossInstance;

public class EvilChickenInstanceController extends BossInstanceController {

	/**
	 * @author: miles M
	 */

	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (object.getId() == 43461) {
			getInstance().leaveInstance(player, BossInstance.EXITED);
			removeController();
			return false;
		}
		return true;
	}
}