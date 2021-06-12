package net.wheeloftime.game.map.bossInstance.impl.godwars;

import net.wheeloftime.game.World;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.map.bossInstance.InstanceSettings;
import net.wheeloftime.game.player.Player;

public class GeneralGraadorInstance extends BossInstance {

	/**
	 * @author: miles M
	 */

	public GeneralGraadorInstance(Player owner, InstanceSettings settings) {
		super(owner, settings);
	}

	@Override
	public int[] getMapPos() {
		return new int[] { 357, 668 };
	}

	@Override
	public int[] getMapSize() {
		return new int[] { 1, 1 };
	}

	@Override
	public void loadMapInstance() {
		World.spawnNPC(6260, getTile(2870, 5369, 0), -1, true, false)
				.setBossInstance(this);
		World.spawnNPC(6261, getTile(2864, 5360, 0), -1, true, false, true)
				.setBossInstance(this);
		World.spawnNPC(6263, getTile(2872, 5353, 0), -1, true, false, true)
				.setBossInstance(this);
		World.spawnNPC(6265, getTile(2867, 5361, 0), -1, true, false, true)
				.setBossInstance(this);
	}
}