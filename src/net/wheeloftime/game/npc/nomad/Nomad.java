package net.wheeloftime.game.npc.nomad;

import java.util.ArrayList;

import net.wheeloftime.game.*;
import net.wheeloftime.game.npc.NPC;
import net.wheeloftime.game.npc.familiar.Familiar;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.QuestManager.Quests;
import net.wheeloftime.game.player.content.FadingScreen;
import net.wheeloftime.game.player.dialogues.Dialogue;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class Nomad extends NPC {

	private int nextMove;
	private long nextMovePerform;
	private WorldTile throneTile;
	private ArrayList<NPC> copies;
	private boolean healed;
	private int notAttacked;
	private Player target;

	public Nomad(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setRun(true);
		setCapDamage(12000);
		setForceTargetDistance(5);
		setNextMovePerform();
	}

	public void setTarget(Player player) {
		target = player;
		super.setTarget(player);
	}

	public void setNextMovePerform() {
		nextMovePerform = Utils.currentTimeMillis()
				+ Utils.random(20000, 30000);
	}

	public boolean isMeleeMode() {
		return nextMove == -1;
	}

	public void setMeleeMode() {
		nextMove = -1;
		setForceFollowClose(true);
	}

	@Override
	public void reset() {
		notAttacked = 0;
		if (nextMove == -1) {
			nextMove = 0;
			setForceFollowClose(false);
		}
		healed = false;
		if (copies != null)
			destroyCopies();
		setNextMovePerform();
		super.reset();

	}

	@Override
	public void sendDeath(Entity source) {
		if (throneTile != null) {
			target.lock();
			target.getVarsManager().sendVarBit(6962, 0);
			Dialogue.sendNPCDialogueNoContinue(target, getId(), 9802,
					"You...<br>You have doomed this world.");
			target.getPackets().sendVoice(8260);
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					Dialogue.closeNoContinueDialogue(target);
					target.getQuestManager().completeQuest(
							Quests.NOMADS_REQUIEM);
					FadingScreen.fade(target, new Runnable() {

						@Override
						public void run() {
							target.getMoneyPouch().setAmount(100000, false);
							target.getControllerManager().forceStop();
						}

					});
				}
			}, getAttackSpeed() + 1);
		}
		super.sendDeath(source);
	}

	@Override
	public void processNPC() {
		Entity target = getCombat().getTarget();
		if (target instanceof Player && !clipedProjectile(target, false)) {
			notAttacked++;
			if (notAttacked == 10) {
				if (copies != null) {
					destroyCopies();
					notAttacked = 0;
					return;
				}
				final Player player = (Player) target;
				setNextForceTalk(new ForceTalk("Face me!"));
				player.getPackets().sendVoice(7992);
			} else if (notAttacked == 20) {
				final Player player = (Player) target;
				setNextForceTalk(new ForceTalk("Coward."));
				player.getPackets().sendVoice(8055);
				reset();
				setNextFaceEntity(null);
				sendTeleport(getThroneTile());
			}
		} else if (target instanceof Familiar && this.target != null)
			super.setTarget(this.target);
		else
			notAttacked = 0;
		super.processNPC();

	}

	// 0 mines
	// 1 wrath
	// 2 multiplies
	// 3 k0 move
	public int getNextMove() {
		if (nextMove == 4)
			nextMove = 0;
		return nextMove++;
	}

	public void setNextMove( int nextMove ) {
		this.nextMove = nextMove;
	}

	public void sendTeleport(final WorldTile tile) {
		setNextAnimation(new Animation(12729));
		setNextGraphics(new Graphics(1576));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				setNextWorldTile(tile);
				setNextAnimation(new Animation(12730));
				setNextGraphics(new Graphics(1577));
				setDirection(6);
			}
		}, 3);
	}

	public boolean useSpecialSpecialMove() {
		return Utils.currentTimeMillis() > nextMovePerform;
	}

	public WorldTile getThroneTile() {

		return throneTile == null ? new WorldTile((getRegionX() << 6) + 32,
				(getRegionY() << 6) + 32, getPlane()) : throneTile;
	}

	public void setThroneTile(WorldTile throneTile) {
		this.throneTile = throneTile;
	}

	public void createCopies(final Player target) {
		setNextAnimation(new Animation(12729));
		setNextGraphics(new Graphics(1576));
		final int thisIndex = Utils.random(4);
		final Nomad thisNpc = this;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				copies = new ArrayList<NPC>();
				setNextNPCTransformation(8529);
				for (int i = 0; i < 4; i++) {
					NPC n;
					if (thisIndex == i) {
						n = thisNpc;
						setNextWorldTile(getCopySpot(i));
					} else {
						n = new FakeNomad(getCopySpot(i), thisNpc);
						copies.add(n);
					}
					n.setCantFollowUnderCombat(true);
					n.setNextAnimation(new Animation(12730));
					n.setNextGraphics(new Graphics(1577));
					n.setTarget(target);
				}

			}
		}, 3);
	}

	private WorldTile getCopySpot( int index ) {
		WorldTile throneTile = getThroneTile();
		switch (index) {
		case 0:
			return throneTile;
		case 1:
			return throneTile.transform(-3, -3, 0);
		case 2:
			return throneTile.transform(3, -3, 0);
		case 3:
		default:
			return throneTile.transform(0, -6, 0);
		}

	}

	void destroyCopy( NPC copy ) {
		copy.finish();
		if (copies == null)
			return;
		copies.remove(copy);
		if (copies.isEmpty())
			destroyCopies();
	}

	private void destroyCopies( ) {
		setNextNPCTransformation(8528);
		setCantFollowUnderCombat(false);
		setNextMovePerform();
		if (copies == null)
			return;
		for (NPC n : copies)
			n.finish();
		copies = null;
	}

	@Override
	public void handleIngoingHit(Hit hit) {
		if (getId() == 8529)
			destroyCopies();
		super.handleIngoingHit(hit);
	}

	public boolean isHealed() {
		return healed;
	}

	public void setHealed(boolean healed) {
		this.healed = healed;
	}

}
