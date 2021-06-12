package net.wheeloftime.game.npc.combat.impl.dung;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.combat.CombatScript;
import net.wheeloftime.game.npc.combat.NPCCombatDefinitions;
import net.wheeloftime.game.npc.familiar.Familiar;

public class BloodragerCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 11106, 11108, 11110, 11112, 11114, 11116, 11118,
				11120, 11122, 11124, 11126 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		npc.getCombatDefinitions();
		boolean usingSpecial = false;
		if (npc instanceof Familiar) {
			Familiar familiar = (Familiar) npc;
			usingSpecial = familiar.hasSpecialOn();
		}
		int tier = (npc.getId() - 11106) / 2;

		int damage = 0;
		if (usingSpecial) {
			npc.setNextGraphics(new Graphics(2444));
			damage = getMaxHit(
					npc,
					(int) (npc.getMaxHit(NPCCombatDefinitions.MELEE) * (1.05 * tier)),
					NPCCombatDefinitions.MELEE, target);
		} else
			damage = getMaxHit(npc, NPCCombatDefinitions.MELEE, target);
		delayHit(npc, usingSpecial ? 1 : 0, target, getMeleeHit(npc, damage));
		npc.setNextAnimation(new Animation(13617));
		return npc.getAttackSpeed();
	}
}
