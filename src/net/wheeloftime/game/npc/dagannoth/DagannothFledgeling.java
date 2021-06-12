package net.wheeloftime.game.npc.dagannoth;

import java.util.ArrayList;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class DagannothFledgeling extends NPC {

	public DagannothFledgeling(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> targets = getPossibleTargets(true, false);
		ArrayList<Entity> targetsCleaned = new ArrayList<>();
		for (Entity t : targets) {
			if (!(t instanceof NPC) || ((NPC) t).getId() != 2879)
				continue;
			targetsCleaned.add(t);
		}
		return targetsCleaned;
	}
}
