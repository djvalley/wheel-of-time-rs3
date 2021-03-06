package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.*;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class Karamthulhuoverlord extends Familiar {

	private static final long serialVersionUID = 6236333946001886534L;

	public Karamthulhuoverlord(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Doomsphere Device";
	}

	@Override
	public String getSpecialDescription() {
		return "Attacks the target with a powerful water spell that can cause up to 160 life points";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 3;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ENTITY;
	}

	@Override
	public boolean submitSpecial(Object object) {
		final Entity target = (Entity) object;
		Player player = getOwner();
		setNextGraphics(new Graphics(7974));
		setNextGraphics(new Graphics(1478));
		player.setNextAnimation(new Animation(7660));
		player.setNextGraphics(new Graphics(1316));
		World.sendProjectile(this, target, 1479, 34, 16, 30, 35, 16, 0);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				target.applyHit(new Hit(getOwner(), Utils.random(163),
						HitLook.MAGIC_DAMAGE));
				target.setNextGraphics(new Graphics(1480));
			}
		}, 2);
		return true;
	}
}
