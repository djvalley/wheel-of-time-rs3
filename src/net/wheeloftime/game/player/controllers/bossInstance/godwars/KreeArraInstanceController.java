package net.wheeloftime.game.player.controllers.bossInstance.godwars;

import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.player.controllers.bossInstance.BossInstanceController;

public class KreeArraInstanceController extends BossInstanceController {

	/**
	 * @author: miles M
	 */

	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (object.getId() == 26426) {
			getInstance().leaveInstance(player, BossInstance.EXITED);
			removeController();
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (object.getId() == 26288) {
			getInstance().leaveInstance(player, BossInstance.EXITED);
			removeController();
			return false;
		}
		return true;
	}
}