package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.ForceTalk;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;

@SuppressWarnings("serial")
public class TreeSpirit extends NPC {

	private Player target;

	public TreeSpirit(Player target, WorldTile tile) {
		super(655, tile, -1, true, true);
		this.target = target;
		target.getTemporaryAttributtes().put("HAS_SPIRIT_TREE", true);
		setTarget(target);
		setNextForceTalk(new ForceTalk(
				"You must defeat me before touching the tree!"));
	}

	@Override
	public void processNPC() {
		if (!target.withinDistance(this, 16)) {
			target.getTemporaryAttributtes().remove("HAS_SPIRIT_TREE");
			finish();
		}
		super.processNPC();
	}

	@Override
	public void sendDeath(Entity source) {
		target.getTemporaryAttributtes().remove("HAS_SPIRIT_TREE");
		target.setKilledLostCityTree(true);
		super.sendDeath(source);

	}

}
