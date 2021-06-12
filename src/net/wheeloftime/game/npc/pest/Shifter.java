package net.wheeloftime.game.npc.pest;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.content.activities.minigames.pest.PestControl;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class Shifter extends PestMonsters {

	public Shifter(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned, int index,
			PestControl manager) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned,
				index, manager);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		Entity target = this.getPossibleTargets().get(0);
		if (this.getCombat().process() && !this.withinDistance(target, 10)
				|| Utils.random(15) == 0)
			teleportSpinner(target);
	}

	private void teleportSpinner(WorldTile tile) { // def 3902, death 3903
		setNextWorldTile(tile);
		setNextAnimation(new Animation(3904));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				setNextGraphics(new Graphics(654));// 1502
			}
		});
	}
}
