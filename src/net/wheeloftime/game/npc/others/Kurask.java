package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.Combat;

@SuppressWarnings("serial")
public class Kurask extends NPC {

	public Kurask(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public void handleIngoingHit(Hit hit) {
		if (hit.getSource() instanceof Player) {
			Player player = (Player) hit.getSource();
			if (!(player.getEquipment().getWeaponId() == 13290 || player
					.getEquipment().getWeaponId() == 4158)
					&& !((player.getCombatDefinitions().getStyle(true) == Combat.RANGE_TYPE || player
							.getCombatDefinitions().getStyle(false) == Combat.RANGE_TYPE) && (player
							.getEquipment().getAmmoId() == 13280 || player
							.getEquipment().getAmmoId() == 4160)))
				hit.setDamage(0);
		}
		super.handleIngoingHit(hit);
	}
}
