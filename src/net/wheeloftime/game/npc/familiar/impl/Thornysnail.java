package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;

public class Thornysnail extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1147053487269627345L;

	public Thornysnail(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Slime Spray";
	}

	@Override
	public String getSpecialDescription() {
		return "Inflicts up to 80 damage against your opponent.";
	}

	@Override
	public int getBOBSize() {
		return 3;
	}

	@Override
	public int getSpecialAmount() {
		return 0;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ENTITY;
	}

	@Override
	public boolean submitSpecial(Object object) {
		return false;
	}
}
