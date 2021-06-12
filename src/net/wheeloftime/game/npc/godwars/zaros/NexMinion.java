package net.wheeloftime.game.npc.godwars.zaros;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.Hit;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.activities.minigames.ZarosGodwars;

@SuppressWarnings("serial")
public class NexMinion extends NPC {

	private boolean hasNoBarrier;

	public NexMinion(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, true, true);
		setCantFollowUnderCombat(true);
	}

	public void breakBarrier() {
		setCapDamage(6000);
		hasNoBarrier = true;
	}

	public boolean isBarrierBroken() {
		return hasNoBarrier;
	}

	@Override
	public void handleIngoingHit(Hit hit) {
		if (!hasNoBarrier) {
			setNextGraphics(new Graphics(1549));
			if (hit.getSource() instanceof Player)
				((Player) hit.getSource()).getPackets().sendGameMessage(
						"The avatar is not weak enough to damage this minion.");
			hit.setDamage(0);
		} else
			super.handleIngoingHit(hit);
	}

	@Override
	public void processNPC() {
		if (isDead() || !hasNoBarrier)
			return;
		if (!getCombat().process())
			checkAgressivity();
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		ZarosGodwars.incrementStage(ZarosGodwars.nex.getCurrentPhase());
	}
}