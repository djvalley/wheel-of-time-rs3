package net.wheeloftime.game.npc.combat.impl;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.Hit.HitLook;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.utils.Utils;

public class TokHaarKetDillCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "TokHaar-Ket-Dill" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(6) == 0) {
			delayHit(
					npc,
					0,
					target,
					getRegularHit(npc, Utils.random(npc
							.getMaxHit(NPCCombatDefinitions.MELEE) + 1)));
			target.setNextGraphics(new Graphics(2999));
			if (target instanceof Player) {
				Player playerTarget = (Player) target;
				playerTarget.getPackets().sendGameMessage(
						"The TokHaar-Ket-Dill slams it's tail to the ground.");
				npc.applyHit(new Hit(playerTarget, 150, HitLook.REGULAR_DAMAGE)); // temp
																					// fix
			}
		} else
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(npc,
							getMaxHit(npc, npc.getAttackStyle(), target)));
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		return npc.getAttackSpeed();
	}
}