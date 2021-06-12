package net.wheeloftime.game.npc.godwars2.helwyr;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class CywirAlpha extends NPC {
	private int phase;

	public CywirAlpha(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setRun(true);
		setIntelligentRouteFinder(true);
		setForceAgressive(true);
	}

	@Override
	public void processNPC() {
		super.processNPC();
	}

	@Override
	public void setRespawnTask() {

	}

	@Override
	public void sendDeath(final Entity source) {
		super.sendDeath(source);
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
