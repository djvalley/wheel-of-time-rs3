package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.item.actions.Consumables;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.game.player.actions.skills.fishing.Fishing.Fish;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;

public class Bunyip extends Familiar {

	private static final long serialVersionUID = 7203440350875823581L;
	private int restoreTicks;

	public Bunyip(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Swallow Whole";
	}

	@Override
	public String getSpecialDescription() {
		return "Eat an uncooked fish and gain the correct number of life points corresponding to the fish eaten if you have the cooking level to cook the fish.";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 7;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ITEM;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		restoreTicks++;
		if (restoreTicks == 20) { // approx 15 secs
			getOwner().heal(20);
			getOwner().setNextGraphics(new Graphics(1507));
			restoreTicks = 0;
		}
	}

	@Override
	public boolean submitSpecial(Object object) {
		Item item = getOwner().getInventory().getItem((Integer) object);
		if (item == null)
			return false;
		for (Fish fish : Fish.values()) {
			if (fish.getId() == item.getId()) {
				if (getOwner().getSkills().getLevel(Skills.COOKING) < fish
						.getLevel()) {
					getOwner()
							.getPackets()
							.sendGameMessage(
									"Your cooking level is not high enough for the bunyip to eat this fish.");
					return false;
				} else {
					getOwner().setNextGraphics(new Graphics(1316));
					getOwner().setNextAnimation(new Animation(7660));
					getOwner()
							.heal(Consumables.getHealAmount(getOwner(), item));
					getOwner().getInventory().deleteItem(item.getId(),
							item.getAmount());
					return true;// stop here
				}
			}
		}
		getOwner().getPackets().sendGameMessage("Your bunyip cannot eat this.");
		return false;
	}
}
