package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class Abyssallurker extends Familiar {

	private static final long serialVersionUID = -6066778023905647435L;

	public Abyssallurker(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Abyssal Stealth";
	}

	@Override
	public String getSpecialDescription() {
		return "Temporarily increases a player's Agility and Thieving by 4 levels.";
	}

	@Override
	public int getBOBSize() {
		return 7;
	}

	@Override
	public int getSpecialAmount() {
		return 3;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.CLICK;
	}

	@Override
	public boolean submitSpecial(Object object) {
		final Player player = (Player) object;
		int newTheiving = player.getSkills().getLevel(Skills.THIEVING) + 4;
		if (newTheiving > player.getSkills().getLevel(Skills.THIEVING) + 4)
			newTheiving = player.getSkills().getLevel(Skills.THIEVING) + 4;
		int newAgility = player.getSkills().getLevel(Skills.AGILITY) + 4;
		if (newAgility > player.getSkills().getLevel(Skills.AGILITY) + 4)
			newAgility = player.getSkills().getLevel(Skills.AGILITY) + 4;
		setNextGraphics(new Graphics(1336));
		setNextAnimation(new Animation(7682));
		player.setNextAnimation(new Animation(7660));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextGraphics(new Graphics(1300));
			}
		}, 3);
		player.getSkills().set(Skills.THIEVING, newTheiving);
		player.getSkills().set(Skills.AGILITY, newAgility);
		return false;
	}
}
