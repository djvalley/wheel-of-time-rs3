package net.wheeloftime.game.npc.godwars.armadyl;

import java.util.ArrayList;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.npc.godwars.zaros.ZarosMinion;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.controllers.Controller;
import net.wheeloftime.game.player.controllers.GodWars;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class GodwarsArmadylFaction extends NPC {

	public GodwarsArmadylFaction(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		if (!withinDistance(new WorldTile(2881, 5306, 0), 200))
			return super.getPossibleTargets();
		else {
			ArrayList<Entity> targets = getPossibleTargets(true, true);
			ArrayList<Entity> targetsCleaned = new ArrayList<Entity>();
			for (Entity t : targets) {
				if (t instanceof GodwarsArmadylFaction
						|| (t instanceof Player && hasGodItem((Player) t))
						|| t instanceof Familiar)
					continue;
				targetsCleaned.add(t);
			}
			return targetsCleaned;
		}
	}

	private boolean hasGodItem(Player player) {
		for (Item item : player.getEquipment().getItems().getItems()) {
			if (item == null)
				continue; // shouldn't happen
			String name = item.getDefinitions().getName().toLowerCase();
			if (name.contains("armadyl") || name.contains("book of law")
					|| ZarosMinion.isNexArmour(name))
				return true;
		}
		return false;
	}

	@Override
	public void sendDeath(final Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					if (source instanceof Player) {
						Player player = (Player) source;
						Controller Controller = player.getControllerManager()
								.getController();
						if (Controller != null && Controller instanceof GodWars) {
							GodWars godController = (GodWars) Controller;
							godController.incrementKillCount(1);
						}
					}
					giveXP();
					drop();
					reset();
					setLocation(getRespawnTile());
					finish();
					if (!isSpawned())
						setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}
}
