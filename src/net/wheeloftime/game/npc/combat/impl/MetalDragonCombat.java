package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Projectile;
import net.wheeloftime.game.World;
import net.wheeloftime.game.EffectsManager.EffectType;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.smithing.DragonfireShield;
import net.wheeloftime.game.player.content.Combat;
import net.wheeloftime.utils.Utils;

public class MetalDragonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Bronze dragon", "Iron dragon", "Steel dragon",
				"Adamant dragon", "Rune dragon", "Celestial dragon" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(2) == 0) {
			int damage = getMaxHit(npc, NPCCombatDefinitions.MAGE, target);
			if (damage > 200 && target instanceof Player) {
				Player player = (Player) target;
				boolean hasSuperPot = player.getEffectsManager()
						.hasActiveEffect(EffectType.SUPER_FIRE_IMMUNITY);
				boolean hasRegularPot = player.getEffectsManager()
						.hasActiveEffect(EffectType.FIRE_IMMUNITY);
				boolean hasShield = Combat.hasAntiDragProtection(target);
				if (hasSuperPot) {
					damage = 0;
					player.getPackets()
							.sendGameMessage(
									"Your potion fully protects you from the dragon's fiery breath.");
				} else {
					if (hasRegularPot)
						damage *= 0.5;
					if (hasShield) {
						player.getPackets()
								.sendGameMessage(
										"Your shield absorbs some of the dragon's fiery breath!");
						damage = hasRegularPot ? 0 : damage / 2;
					}
				}
				DragonfireShield.chargeDFS(player, false);
			}
			npc.setNextAnimation(new Animation(13160));
			Projectile projectile = World.sendProjectileNew(npc, target, 2464,
					28, 16, 35, 2, 16, 0);
			delayHit(npc,
					Utils.projectileTimeToCycles(projectile.getEndTime()) - 1,
					target, getRegularHit(npc, damage));
		} else {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(npc,
							getMaxHit(npc, NPCCombatDefinitions.MELEE, target)));
		}
		return npc.getAttackSpeed();
	}

}
