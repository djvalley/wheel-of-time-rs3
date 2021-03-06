package net.wheeloftime.game.npc.evilchicken;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;

@SuppressWarnings("serial")
public class EvilChicken extends NPC {

	/**
	 * @author: miles M
	 */

	public EvilChicken(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(3000);
		setIntelligentRouteFinder(true);
	}

	public static boolean distanceOf(WorldTile tile) {
		return (tile.getX() >= 2683 && tile.getX() <= 2619)
				&& (tile.getY() >= 10369 && tile.getY() <= 10435);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
		checkReset();
	}

	private void checkReset() {
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && !isUnderCombat()
				&& getPossibleTargets().isEmpty())
			setHitpoints(maxhp);
	}

	@Override
	public void sendDeath(Entity source) {
		// increaseKills(RecordKey.EVIL_CHICKEN, true);
		super.sendDeath(source);
	}

	@Override
	public void handleIngoingHit(final Hit hit) {
		super.handleIngoingHit(hit);
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.8;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.8;
	}
}