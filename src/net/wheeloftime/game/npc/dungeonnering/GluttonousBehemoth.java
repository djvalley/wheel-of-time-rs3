package net.wheeloftime.game.npc.dungeonnering;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public final class GluttonousBehemoth extends DungeonBoss {

	private WorldObject heal;
	private int ticks;

	public GluttonousBehemoth(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference) {
		super(id, tile, manager, reference);
		setCantFollowUnderCombat(true);
	}

	public void setHeal(WorldObject food) {
		ticks = 0;
		heal = food;
		removeTarget();
	}

	@Override
	public void processNPC() {
		if (heal != null) {
			setNextFaceEntity(null);
			ticks++;
			if (ticks == 1) {
				calcFollow(heal, true);
			} else if (ticks == 5) {
				setNextAnimation(new Animation(13720));
			} else if (ticks < 900 && ticks > 7) {
				if (getHitpoints() >= (getMaxHitpoints() * 0.75)) {
					setNextAnimation(new Animation(-1));
					calcFollow(getRespawnTile(), true);
					ticks = 995;
					return;
				}
				heal(50 + Utils.random(50));
				setNextAnimation(new Animation(13720));
			} else if (ticks > 1000)
				heal = null;
			return;
		}
		super.processNPC();
	}

}
