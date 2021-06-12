package net.wheeloftime.game.npc.nomad;

import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
class FakeNomad extends NPC {

	private Nomad nomad;

	FakeNomad(WorldTile tile, Nomad nomad) {
		super(8529, tile, -1, true, true);
		this.nomad = nomad;
	}

	@Override
	public void handleIngoingHit(Hit hit) {
		nomad.destroyCopy(this);
	}

}
