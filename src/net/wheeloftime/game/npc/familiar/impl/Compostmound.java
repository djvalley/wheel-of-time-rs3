package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;

public class Compostmound extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5013658417553282084L;

	public Compostmound(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Generate Compost";
	}

	@Override
	public String getSpecialDescription() {
		return "Fill a nearby compost bin with compost, with a chance of creating super compost.";
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
		return SpecialAttack.CLICK;
	}

	@Override
	public boolean submitSpecial(Object object) {
		Player player = (Player) object;
		int newLevel = (int) (player.getSkills().getLevel(Skills.FARMING) + 1 + (Math
				.round(player.getSkills().getLevel(Skills.FARMING) * .02)));
		if (newLevel > player.getSkills().getLevel(Skills.FARMING)
				+ 1
				+ (Math.round(player.getSkills().getLevel(Skills.FARMING) * .02)))
			newLevel = (int) (player.getSkills().getLevel(Skills.FARMING) + 1 + (Math
					.round(player.getSkills().getLevel(Skills.FARMING) * .02)));
		player.setNextGraphics(new Graphics(1300));
		player.setNextAnimation(new Animation(7660));
		setNextGraphics(new Graphics(1312));
		player.getSkills().set(Skills.FARMING, newLevel);
		return true;
	}

}
