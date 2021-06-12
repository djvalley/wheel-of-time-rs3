package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.ForceTalk;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.utils.Utils;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class Ducks extends NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5925273298034153386L;
	private static final String[] noises = { "Quack!", "Quack?",
			"Meow?..o'wait Quack!" };

	public Ducks(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public void processNPC() {
		int ticks = Utils.random(45);
		if (ticks == 45) {
			String noise = noises[Utils.random(noises.length)];
			setNextForceTalk(new ForceTalk(noise));
		}

	}
}
