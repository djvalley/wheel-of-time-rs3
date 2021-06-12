package net.wheeloftime.game.npc.others;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class Strykewyrm extends NPC {

	private int nonCombatTicks;

	public Strykewyrm(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, false);
		nonCombatTicks = 1;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead() || getId() % 2 == 0 || isCantInteract())
			return;
		if (!isUnderCombat())
			nonCombatTicks++;
		if (nonCombatTicks % 17 == 0) {
			setNextAnimation(new Animation(12796));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					reset();
				}
			});
		}
	}

	@Override
	public void reset() {
		setNextNPCTransformation(getId() - 1);
		setCantInteract(false);
		nonCombatTicks = 1;
		super.reset();
	}

	public static void handleStomping(final Player player, final NPC npc) {
		if (npc.isCantInteract())
			return;
		int requiredLevel = npc.getId() == 20629 ? 94
				: npc.getId() == 9462 ? 93 : npc.getId() == 9464 ? 77 : npc
						.getId() == 9466 ? 73 : 1;
		if (player.getSkills().getLevel(Skills.SLAYER) < requiredLevel) {
			player.getPackets().sendGameMessage(
					"You need a slayer level of at least " + requiredLevel
							+ " to fight that stykewyrm.");
			return;
		}
		player.setNextAnimation(new Animation(4278));
		npc.setCantInteract(true);
		WorldTasksManager.schedule(new WorldTask() {

			int ticks;

			@Override
			public void run() {
				ticks++;
				if (ticks == 2) {
					npc.setNextAnimation(new Animation(12795));
					npc.setNextNPCTransformation(npc.getId() + 1);
					npc.setHitpoints(npc.getMaxHitpoints());
				} else if (ticks == 4) {
					npc.getCombat().setTarget(player);
					npc.setCantInteract(false);
					stop();
					return;
				}
			}
		}, 0, 0);
	}
}
