package net.wheeloftime.game.npc.pest;

import java.util.ArrayList;
import java.util.List;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.activities.minigames.pest.PestControl;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class PestMonsters extends NPC {

	protected PestControl manager;
	protected int portalIndex;

	public PestMonsters(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned, int index,
			PestControl manager) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		this.manager = manager;
		this.portalIndex = index;
		setForceAgressive(true);
		setForceTargetDistance(70);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (!getCombat().underCombat())
			checkAgressivity();
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> possibleTarget = new ArrayList<Entity>();
		List<Integer> playerIndexes = World.getRegion(getRegionId())
				.getPlayerIndexes();
		if (playerIndexes != null) {
			for (int playerIndex : playerIndexes) {
				Player player = World.getPlayers().get(playerIndex);
				if (player == null || player.isDead() || player.hasFinished()
						|| !player.isRunning()
						|| !player.withinDistance(this, 10))
					continue;
				possibleTarget.add(player);
			}
		}
		if (possibleTarget.isEmpty() || Utils.random(3) == 0) {
			possibleTarget.clear();
			possibleTarget.add(manager.getKnight());
		}
		return possibleTarget;
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		manager.getPestCounts()[portalIndex]--;
	}
}
