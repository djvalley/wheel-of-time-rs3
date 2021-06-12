package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.*;
import net.wheeloftime.game.item.actions.Consumables;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class Ravenouslocust extends Familiar {

	private static final long serialVersionUID = -176892505925306625L;

	public Ravenouslocust(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Famine";
	}

	@Override
	public String getSpecialDescription() {
		return "Eats a peice of an opponent's food.";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 12;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ENTITY;
	}

	@Override
	public boolean submitSpecial(Object object) {
		final Entity target = (Entity) object;
		final Familiar npc = this;
		setNextGraphics(new Graphics(1346));
		setNextAnimation(new Animation(7998));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				World.sendProjectile(npc, target, 1347, 34, 16, 30, 35, 16, 0);
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						target.setNextGraphics(new Graphics(1348));
						if (target instanceof Player) {
							Player playerTarget = (Player) target;

							int nextFoodSlot = Consumables
									.getNextFoodSlot(playerTarget);
							if (nextFoodSlot == -1)
								return;
							playerTarget.getInventory().deleteItem(
									playerTarget.getInventory().getItem(
											nextFoodSlot));
						}
					}
				}, 2);
			}
		});
		return true;
	}
}
