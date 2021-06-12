package net.wheeloftime.game.map.bossInstance.impl;

import net.wheeloftime.game.World;
import net.wheeloftime.game.map.bossInstance.BossInstance;
import net.wheeloftime.game.map.bossInstance.InstanceSettings;
import net.wheeloftime.game.npc.kalphite.KalphiteKing;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.TimersManager.RecordKey;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

public class KalphiteKingInstance extends BossInstance {

	public KalphiteKingInstance(Player owner, InstanceSettings settings) {
		super(owner, settings);
	}

	@Override
	public int[] getMapPos() {
		return new int[] { 368, 216 };
	}

	@Override
	public int[] getMapSize() {
		return new int[] { 1, 1 };
	}

	@Override
	public void leaveInstance(Player player, int type) {
		player.getTimersManager().removeTimer(RecordKey.KALPHITE_KING);
		super.leaveInstance(player, type);
	}

	@Override
	public void loadMapInstance() {
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (!KalphiteKingInstance.this.isInstanceReady())
					return;
				KalphiteKing king = (KalphiteKing) World.spawnNPC(
						16697 + Utils.random(3), getTile(2974, 1759, 0), -1,
						true);// can spawn all colours
				king.setBossInstance(KalphiteKingInstance.this);
				king.setPhase(0);
				king.addTimer(); // king wont set timer by itself first time due
				// to boss instance not being added yet
			}

		}, 10);
	}
}