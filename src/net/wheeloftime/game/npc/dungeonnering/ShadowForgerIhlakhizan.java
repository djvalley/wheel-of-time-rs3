package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.TemporaryAtributtes.Key;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.Drop;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public final class ShadowForgerIhlakhizan extends DungeonBoss {

	public ShadowForgerIhlakhizan(int id, WorldTile tile,
			DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		setCantFollowUnderCombat(true); // force cant walk
	}

	@Override
	public void setNextFaceEntity(Entity entity) {
		// this boss doesnt face
	}

	@Override
	public void processNPC() {
		if (isDead())
			return;
		super.processNPC();
		for (Player player : getManager().getParty().getTeam()) {
			if (!getManager().isAtBossRoom(player)
					|| clipedProjectile(player, false)
					|| player.getTemporaryAttributtes().get(
							Key.SHADOW_FORGER_SHADOW) != null)
				continue;
			player.setNextGraphics(new Graphics(2378));
			player.getTemporaryAttributtes().put(Key.SHADOW_FORGER_SHADOW,
					Boolean.TRUE);
			player.applyHit(new Hit(this, Utils.random((int) (player
					.getMaxHitpoints() * 0.1)) + 1, HitLook.REGULAR_DAMAGE));
		}
	}

	public void setUsedShadow() {
		for (Player player : getManager().getParty().getTeam()) {
			player.getTemporaryAttributtes().put(Key.SHADOW_FORGER_SHADOW,
					Boolean.TRUE);
		}
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public Item sendDrop(Player player, Drop drop) {
		Item item = new Item(drop.getItemId());
		player.getInventory().addItemDrop(item.getId(), item.getAmount());
		return item;
	}

}
