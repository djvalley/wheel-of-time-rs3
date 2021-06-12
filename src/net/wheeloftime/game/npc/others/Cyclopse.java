package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.godwars.bandos.GodwarsBandosFaction;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.activities.minigames.WarriorsGuild;
import net.wheeloftime.game.player.controllers.Controller;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class Cyclopse extends GodwarsBandosFaction {

	public Cyclopse(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, false);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		if (source instanceof Player) {
			WarriorsGuild.killedCyclopses++;
			final NPC npc = this;
			final Player player = (Player) source;
			Controller controller = player.getControllerManager()
					.getController();
			if (controller == null || !(controller instanceof WarriorsGuild)
					|| Utils.random(15) != 0)
				return;
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					World.addGroundItem(
							new Item(WarriorsGuild.getBestDefender(player)),
							new WorldTile(getCoordFaceX(npc.getSize()),
									getCoordFaceY(npc.getSize()), getPlane()),
							player, true, 60);
				}
			}, getCombatDefinitions().getDeathDelay());
		}
	}
}