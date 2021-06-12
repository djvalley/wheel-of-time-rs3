package net.wheeloftime.game.map.bossInstance.impl.godwars;

import net.wheeloftime.game.World;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.map.bossInstance.InstanceSettings;
import net.wheeloftime.game.player.Player;

public class KreeArraInstance extends BossInstance {

	/**
	 * @author: miles M
	 */

	public KreeArraInstance(Player owner, InstanceSettings settings) {
		super(owner, settings);
	}

	@Override
	public int[] getMapPos() {
		return new int[] { 351, 661 };
	}

	@Override
	public int[] getMapSize() {
		return new int[] { 1, 1 };
	}

	@Override
	public void loadMapInstance() {
		World.spawnNPC(6222, getTile(2832, 5302, 0), -1, true, false)
				.setBossInstance(this);
		World.spawnNPC(6225, getTile(2828, 5299, 0), -1, true, false, true)
				.setBossInstance(this);
		World.spawnNPC(6227, getTile(2833, 5297, 0), -1, true, false, true)
				.setBossInstance(this);
		World.spawnNPC(6223, getTile(2838, 5303, 0), -1, true, false, true)
				.setBossInstance(this);
	}
}