package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;

public class Desertwyrm extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 678861520073043877L;

	public Desertwyrm(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Electric Lash";
	}

	@Override
	public String getSpecialDescription() {
		return "Attacks the player's opponent inflicting up to 50 damage instead of 40 damage. ";
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
		return SpecialAttack.ENTITY;
	}

	@Override
	public boolean submitSpecial(Object object) {
		return false;
	}

}