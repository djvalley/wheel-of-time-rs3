package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;

@SuppressWarnings("serial")
public class DungeonSkeletonBoss extends DungeonNPC {

	private DivineSkinweaver boss;

	public DungeonSkeletonBoss(int id, WorldTile tile, DungeonManager manager,
			double multiplier) {
		super(id, tile, manager, multiplier);
		setForceAgressive(true);
		setIntelligentRouteFinder(true);
		setLureDelay(0);
		boss = (DivineSkinweaver) getNPC(10058);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		boss.removeSkeleton(this);
	}

	@Override
	public void drop() {

	}

}
