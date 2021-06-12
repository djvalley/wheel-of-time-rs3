package net.wheeloftime.game.npc.others;

import java.util.ArrayList;
import java.util.List;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class RiseOfTheSixNPC extends NPC {

	public RiseOfTheSixNPC(int id, WorldTile tile) {
		super(id, tile, -1, true, true);
		setForceMultiArea(true);
		setNoDistanceCheck(true);
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		final ArrayList<Entity> possibleTarget = new ArrayList<Entity>(1);
		final List<Integer> playerIndexes = World.getRegion(getRegionId())
				.getPlayerIndexes();
		if (playerIndexes != null) {
			for (final int npcIndex : playerIndexes) {
				final Player player = World.getPlayers().get(npcIndex);
				if (player == null || player.isDead() || player.hasFinished()
						|| !player.isRunning())
					continue;
				possibleTarget.add(player);
			}
		}
		return possibleTarget;
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
	}
}