package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class Jadinko extends NPC {

	public Jadinko(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		if (source instanceof Player) {
			Player player = (Player) source;
			player.setFavorPoints((getId() == 13820 ? 3 : getId() == 13821 ? 7
					: 10) + player.getFavorPoints());
		}
	}
}