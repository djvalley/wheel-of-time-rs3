package net.wheeloftime.game.npc.familiar.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.summoning.Summoning.Pouch;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class Macaw extends Familiar {

	private static final long serialVersionUID = -7805271915467121215L;
	private int specialLock = -1;

	public Macaw(Player owner, Pouch pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Herbcall";
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (specialLock > 0)
			specialLock--;
		else if (specialLock == 0) {
			specialLock = -1;
			getOwner().getPackets().sendGameMessage(
					"Your macaw feels rested and ready for flight again.");
		}
	}

	@Override
	public String getSpecialDescription() {
		return "Can produce one herb, all herbs up to and including Torstol, within a 60 second range.";
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
		if (specialLock > 0) {
			getOwner().getPackets().sendGameMessage(
					"Your macaw is too tired to continue searching for herbs.");
			return false;
		}
		specialLock = 100;
		getOwner().setNextGraphics(new Graphics(1300));
		getOwner().setNextAnimation(new Animation(7660));
		setNextAnimation(new Animation(8013));
		final WorldTile tile = new WorldTile(getOwner().getX() - 1, getOwner()
				.getY(), getOwner().getPlane());
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				World.sendGraphics(getOwner(), new Graphics(1321), tile);
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						setNextAnimation(new Animation(8014));
					}
				}, 3);
			}
		}, 2);
		return true;
	}
}
