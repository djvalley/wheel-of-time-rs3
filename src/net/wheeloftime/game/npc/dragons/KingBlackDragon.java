package net.wheeloftime.game.npc.dragons;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.TimersManager.RecordKey;

@SuppressWarnings("serial")
public class KingBlackDragon extends NPC {

	public KingBlackDragon(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setDropRateFactor(2);
		setIntelligentRouteFinder(true);
	}

	@Override
	public void sendDeath(final Entity source) {
		if (source instanceof Player)
			((Player) source).getTimersManager().removeTimer(
					RecordKey.KING_BLACK_DRAGON);
		increaseKills(RecordKey.KING_BLACK_DRAGON, false);
		super.sendDeath(source);
	}

}
