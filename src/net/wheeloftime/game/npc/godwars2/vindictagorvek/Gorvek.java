package net.wheeloftime.game.npc.godwars2.vindictagorvek;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class Gorvek extends NPC {

	public Gorvek(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setRun(true);
	}

	@Override
	public void sendDeath(final Entity source) {
		super.sendDeath(source);
	}

	@Override
	public void spawn() {
		super.spawn();
		setNextAnimation(new Animation(28264));
	}

}