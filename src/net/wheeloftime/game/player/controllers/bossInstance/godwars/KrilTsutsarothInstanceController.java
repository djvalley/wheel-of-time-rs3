package net.wheeloftime.game.player.controllers.bossInstance.godwars;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.player.controllers.bossInstance.BossInstanceController;

public class KrilTsutsarothInstanceController extends BossInstanceController {

	/**
	 * @author: miles M
	 */

	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (object.getId() == 26428) {
			getInstance().leaveInstance(player, BossInstance.EXITED);
			removeController();
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (object.getId() == 26286) {
			getInstance().leaveInstance(player, BossInstance.EXITED);
			removeController();
			return false;
		}
		return true;
	}
}