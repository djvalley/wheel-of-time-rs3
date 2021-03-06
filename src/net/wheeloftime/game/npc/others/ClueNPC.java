package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class ClueNPC extends NPC {

	private Player target;

	public ClueNPC(Player target, int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, true);
		this.target = target;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (target.hasFinished() || !withinDistance(target, 10)) {
			target.getTreasureTrailsManager().setPhase(0);
			finish();
		}
	}

	@Override
	public void drop() {
		target.getTreasureTrailsManager().setPhase(2);
	}

	public Player getTarget() {
		return target;
	}
}
