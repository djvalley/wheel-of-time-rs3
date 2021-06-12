package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class AbyssalDemon extends NPC {

	public AbyssalDemon(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		Entity target = getCombat().getTarget();
		if (target != null
				&& Utils.isOnRange(target.getX(), target.getY(),
						target.getSize(), getX(), getY(), getSize(), 4)
				&& Utils.random(50) == 0) {
			sendTeleport(target);
			sendTeleport(this);
		}
	}

	private void sendTeleport(Entity entity) {
		entity.setNextGraphics(new Graphics(409));
		entity.setNextWorldTile(Utils.getFreeTile(new WorldTile(entity), 1));
	}
}
