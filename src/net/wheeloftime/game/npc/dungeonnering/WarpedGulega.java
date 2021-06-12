package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;

@SuppressWarnings("serial")
public class WarpedGulega extends DungeonBoss {

	public WarpedGulega(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference) {
		super(id, tile, manager, reference);
	}

	// thats default lol
	/*
	 * @Override public double getMeleePrayerMultiplier() { return 0.0;//Fully
	 * block it. }
	 * 
	 * @Override public double getRangePrayerMultiplier() { return 0.0;//Fully
	 * block it. }
	 * 
	 * @Override public double getMagePrayerMultiplier() { return 0.0;//Fully
	 * block it. }
	 */

	@Override
	public void processHit(Hit hit) {
		if (!(hit.getSource() instanceof Familiar))
			hit.setDamage((int) (hit.getDamage() * 0.45D));
		super.processHit(hit);
	}
}
