package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class Nechryael extends NPC {

	public Nechryael(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
	}

	@Override
	public void processNPC() {
		super.processNPC();
	}

}