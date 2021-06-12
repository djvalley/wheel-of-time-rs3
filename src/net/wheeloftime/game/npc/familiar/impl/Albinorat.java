package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;

public class Albinorat extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 558701463128149919L;

	public Albinorat(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Cheese Feast";
	}

	@Override
	public String getSpecialDescription() {
		return "Fills your inventory with four peices of cheese.YUM.";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 6;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.CLICK;
	}

	@Override
	public boolean submitSpecial(Object object) {
		Player player = (Player) object;
		player.setNextGraphics(new Graphics(1316));
		player.setNextAnimation(new Animation(7660));
		player.getInventory().addItem(new Item(1985, 4));
		return true;
	}
}
