package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonConstants;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;
import net.wheeloftime.utils.Utils;

public class Cubhoardstalker extends Familiar {

	private static final long serialVersionUID = -7037718748109234870L;
	private int forageTicks;

	public Cubhoardstalker(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		forageTicks++;
		if (forageTicks == 300) {
			forageTicks = 0;
			getBob().getBeastItems().add(
					new Item(DungeonConstants.HOARDSTALKER_ITEMS[0][Utils
							.random(5)], 1));
		}
	}

	@Override
	public String getSpecialName() {
		return "Aptitude";
	}

	@Override
	public String getSpecialDescription() {
		return "Boosts all of your non-combat skills by 1.";
	}

	@Override
	public int getBOBSize() {
		return 30;
	}

	@Override
	public int getSpecialAmount() {
		return 20;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.CLICK;
	}

	@Override
	public boolean submitSpecial(Object object) {
		Player player = (Player) object;
		player.setNextGraphics(new Graphics(1300));
		player.setNextAnimation(new Animation(7660));
		for (int skill = 0; skill < Skills.SKILL_NAME.length; skill++) {
			if (skill == Skills.SUMMONING || skill == Skills.ATTACK
					|| skill == Skills.DEFENCE || skill == Skills.STRENGTH
					|| skill == Skills.RANGED || skill == Skills.MAGIC
					|| skill == Skills.PRAYER || skill == Skills.DUNGEONEERING)
				continue;
			player.getSkills().set(skill,
					player.getSkills().getLevel(skill) + 1);
		}
		return true;
	}
}
