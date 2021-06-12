package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.World;
import net.wheeloftime.game.item.ItemIdentifiers;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.slayer.Slayer;
import net.wheeloftime.utils.Utils;

public class AberrantSpectre extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Aberrant spectre" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions def = npc.getCombatDefinitions();
		if (!Slayer.hasNosepeg(target)) {
			Player targetPlayer = (Player) target;
			if (!(targetPlayer.getPrayer().isMageProtecting()
					|| targetPlayer.getEquipment().containsOneItem(
							ItemIdentifiers.SLAYER_HELMET) || targetPlayer
					.getEquipment().containsOneItem(ItemIdentifiers.FACE_MASK))) {
				int randomSkill = Utils.random(0, 6);
				int currentLevel = targetPlayer.getSkills().getLevel(
						randomSkill);
				targetPlayer.getSkills().set(randomSkill,
						currentLevel < 5 ? 0 : currentLevel - 5);
				targetPlayer
						.getPackets()
						.sendGameMessage(
								"The smell of the abberrant spectre make you feel slightly weaker.",
								true);
			}
			delayHit(npc, 1, target,
					getMagicHit(npc, targetPlayer.getMaxHitpoints() / 10));
			// TODO player emote hands on ears
		} else
			delayHit(
					npc,
					1,
					target,
					getMagicHit(npc,
							getMaxHit(npc, npc.getAttackStyle(), target)));
		World.sendProjectile(npc, target, def.getAttackProjectile(), 18, 18,
				50, 25, 0, 0);
		npc.setNextAnimation(new Animation(def.getAttackEmote()));
		return npc.getAttackSpeed();
	}
}