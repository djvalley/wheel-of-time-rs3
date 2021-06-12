package net.wheeloftime.game.map.bossInstance.impl;

import net.wheeloftime.game.World;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.map.bossInstance.InstanceSettings;
import net.wheeloftime.game.player.Player;

public class AgorothInstance extends BossInstance {

	public AgorothInstance(Player owner, InstanceSettings settings) {
		super(owner, settings);
	}

	@Override
	public int[] getMapPos() {
		return new int[] { 482, 848 };
	}

	@Override
	public int[] getMapSize() {
		return new int[] { 3, 3 };
	}

	@Override
	public void loadMapInstance() {
		World.spawnNPC(19332, getTile(3872, 6823, 0), -1, true)
				.setBossInstance(this);
	}

}