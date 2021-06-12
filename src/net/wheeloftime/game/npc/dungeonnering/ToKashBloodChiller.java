package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;

@SuppressWarnings("serial")
public class ToKashBloodChiller extends DungeonBoss {

	private boolean specialAttack;

	public ToKashBloodChiller(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference) {
		super(id, tile, manager, reference);
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 1.5;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 1.5;
	}

	public void setSpecialAttack(boolean specialAttack) {
		this.specialAttack = specialAttack;
	}

	public boolean canSpecialAttack() {
		return !specialAttack;
	}
}
