package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.Drop;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonUtils;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;

@SuppressWarnings("serial")
public class Sagittare extends DungeonBoss {

	private int stage;
	private boolean special;

	public Sagittare(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference) {
		super(id, tile, manager, reference);
		setCantFollowUnderCombat(true);
		stage = 3;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		int max_hp = getMaxHitpoints();
		int current_hp = getHitpoints();

		if ((current_hp == 1 || current_hp < max_hp * (.25 * stage))
				&& !special) {
			special = true;
			stage--;
		}
	}

	@Override
	public void processHit(Hit hit) {
		int damage = hit.getDamage();
		if (damage > 0) {
			if (hit.getLook() == HitLook.RANGE_DAMAGE)
				hit.setDamage((int) (damage * .4));
		}
		super.processHit(hit);
	}

	public boolean isUsingSpecial() {
		return special;
	}

	public void setUsingSpecial(boolean special) {
		this.special = special;
	}

	public int getStage() {
		return stage;
	}

	@Override
	public void sendDeath(final Entity source) {
		super.sendDeath(source);
	}

	@Override
	public Item sendDrop(Player player, Drop drop) {
		int tier = (drop.getItemId() - 16317) / 2 + 1;
		player.getInventory().addItemDrop(DungeonUtils.getArrows(tier), 125);
		return super.sendDrop(player, drop);
	}
}
