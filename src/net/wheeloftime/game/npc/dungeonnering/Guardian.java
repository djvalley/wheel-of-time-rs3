package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;

@SuppressWarnings("serial")
public class Guardian extends DungeonNPC {

	private RoomReference reference;

	public Guardian(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference, double multiplier) {
		super(id, tile, manager, multiplier);
		this.reference = reference;
		setForceAgressive(true);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		getManager().updateGuardian(reference);
	}

}
