package net.wheeloftime.game.player.controllers.trollinvasion;

import java.util.ArrayList;
import java.util.List;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class TrollInvasionNPC extends NPC {

	private TrollInvasion Controller;

	public TrollInvasionNPC(int id, WorldTile tile, TrollInvasion Controller) {
		super(id, tile, -1, true, true);
		this.Controller = Controller;
		setForceMultiArea(true);
		setNoDistanceCheck(true);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		Controller.addKill();
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> possibleTarget = new ArrayList<Entity>(1);
		List<Integer> playerIndexes = World.getRegion(getRegionId())
				.getPlayerIndexes();
		if (playerIndexes != null) {
			for (int npcIndex : playerIndexes) {
				Player player = World.getPlayers().get(npcIndex);
				if (player == null || player.isDead() || player.hasFinished()
						|| !player.isRunning())
					continue;
				possibleTarget.add(player);
			}
		}
		return possibleTarget;
	}

}
