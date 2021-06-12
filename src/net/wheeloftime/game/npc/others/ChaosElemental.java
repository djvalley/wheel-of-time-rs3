package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.TimersManager.RecordKey;

@SuppressWarnings("serial")
public class ChaosElemental extends NPC {

	public ChaosElemental(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setIntelligentRouteFinder(true);
	}

	@Override
	public void sendDeath(Entity source) {
		if (source instanceof Player)
			((Player) source).getTimersManager().removeTimer(
					RecordKey.CHAOS_ELEMENTAL);
		increaseKills(RecordKey.CHAOS_ELEMENTAL, false);
		super.sendDeath(source);
	}
}
