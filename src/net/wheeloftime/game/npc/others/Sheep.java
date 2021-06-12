package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class Sheep extends NPC {

	int ticks, origonalId;

	public Sheep(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		origonalId = id;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (getId() != origonalId) {
			if (ticks++ == 60) {
				setNextNPCTransformation(origonalId);
				ticks = 0;
			}
		}
	}
}
