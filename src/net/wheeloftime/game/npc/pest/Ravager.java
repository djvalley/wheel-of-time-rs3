package net.wheeloftime.game.npc.pest;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.content.activities.minigames.pest.PestControl;

@SuppressWarnings("serial")
public class Ravager extends PestMonsters {

	boolean destroyingObject = false;

	public Ravager(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned, int index,
			PestControl manager) {
		super(id, tile, -1, false, false, index, manager);
	}

	@Override
	public void processNPC() {
		super.processNPC();

	}
}
