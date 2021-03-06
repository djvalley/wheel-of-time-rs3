package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class NPCActor extends NPC {

	private Player target;

	public NPCActor(int id, String name, int combat, WorldTile tile,
			Player target) {
		super(id, tile, -1, true, true);
		this.target = target;
		setRandomWalk(0);
		if (name != null) {
			setName(name);
		}
		if (combat != -1) {
			setCombatLevel(combat);
		}
	}

	@Override
	public boolean withinDistance(Player tile, int distance) {
		return tile == target && super.withinDistance(tile, distance);
	}

	@Override
	public boolean checkAgressivity() {
		return false;
	}

}
