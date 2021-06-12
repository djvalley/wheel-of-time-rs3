package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.skills.DungeoneeringFishing.Fish;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class DungeonFishSpot extends DungeonNPC {

	private Fish fish;
	private int fishes;

	public DungeonFishSpot(int id, WorldTile tile, DungeonManager manager,
			Fish fish) {
		super(id, tile, manager, 1);
		this.fish = fish;
		setName(Utils.formatPlayerNameForDisplay(fish.toString()));
		fishes = 14;
	}

	@Override
	public void processNPC() {

	}

	public Fish getFish() {
		return fish;
	}

	public int decreaseFishes() {
		return fishes--;
	}

	public void addFishes() {
		fishes += Utils.random(5, 10);
	}
}
