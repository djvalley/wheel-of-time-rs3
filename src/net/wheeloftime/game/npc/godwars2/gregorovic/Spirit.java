package net.wheeloftime.game.npc.godwars2.gregorovic;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class Spirit extends NPC {

	private int phase;

	public Spirit(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setIntelligentRouteFinder(true);
	}

	@Override
	public void sendDeath(final Entity source) {
		Player player = (Player) source;
		super.sendDeath(source);
	}

	@Override
	public void spawn() {
	}

	public int getPhase() {
		return phase;
	}

	public void nextPhase() {
		phase++;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

}
