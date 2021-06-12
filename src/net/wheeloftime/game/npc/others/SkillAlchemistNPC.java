package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class SkillAlchemistNPC extends NPC {

	public SkillAlchemistNPC(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		setName("Skill Alchemist");
	}
}
