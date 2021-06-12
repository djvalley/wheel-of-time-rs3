package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.activities.minigames.RunespanController;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class YellowWizard extends NPC {

	private RunespanController Controller;
	private long spawnTime;

	public YellowWizard(WorldTile tile, RunespanController Controller) {
		super(15430, tile, -1, true, true);
		spawnTime = Utils.currentTimeMillis();
		this.Controller = Controller;
	}

	@Override
	public void processNPC() {
		if (spawnTime + 300000 < Utils.currentTimeMillis())
			finish();
	}

	@Override
	public void finish() {
		Controller.removeWizard();
		super.finish();
	}

	public static void giveReward(Player player) {

	}

	@Override
	public boolean withinDistance(Player tile, int distance) {
		return tile == Controller.getPlayer()
				&& super.withinDistance(tile, distance);
	}

}
