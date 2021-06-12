package net.wheeloftime.game.npc.araxxor;

import net.wheeloftime.game.Graphics;
import net.wheeloftime.game.World;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class EggAttack implements AraxxorAttack {

	int X;
	int Y;
	WorldObject One;
	WorldObject Two;
	WorldObject Three;

	@Override
	public int attack(final Araxxor npc, final Player p) {
		final WorldTile SpawnSpiders = new WorldTile(X, Y, p.getPlane());

		if (!p.hasSpawnedEggs) {
			p.hasSpawnedEggs = true;
			p.AraxxorEggBurst = false;
			WorldTasksManager.schedule(new WorldTask() {
				int time;

				@Override
				public void run() {
					time++;
					npc.setPhase(1);
					p.AraxxorAttackCount++;
					@SuppressWarnings("unused")
					int hit = 0;
					p.hasSpawnedEggs = true;
					if (time == 1) {
						X = p.FINALAGGX;
						Y = p.FINALAGGY;
						p.eggSpidersX = X;
						p.eggSpidersY = Y;
						// p.sendMessage(""+p.FINALAGGX+" "+p.FINALAGGY+"");
						World.sendGraphics(null, new Graphics(5009),
								new WorldTile(X + 3, Y, p.getPlane()));
						World.sendGraphics(null, new Graphics(5009),
								new WorldTile(X + 2, Y, p.getPlane()));
						World.sendGraphics(null, new Graphics(5009),
								new WorldTile(X + 2, Y + 1, p.getPlane()));
					}
					if (time == 3) {
						WorldObject object = new WorldObject(91641, 22, 1,
								X + 3, Y, p.getPlane(), p);
						WorldObject object2 = new WorldObject(91641, 22, 1,
								X + 2, Y, p.getPlane(), p);
						WorldObject object3 = new WorldObject(91641, 22, 1,
								X + 2, Y + 1, p.getPlane(), p);
						World.spawnObject(new WorldObject(91641, 22, 1, X + 3,
								Y, p.getPlane()));
						World.spawnObject(new WorldObject(91641, 22, 1, X + 2,
								Y, p.getPlane()));
						World.spawnObject(new WorldObject(91641, 22, 1, X + 2,
								Y + 1, p.getPlane()));
						One = object;
						Two = object2;
						Three = object3;
					}
					if (time >= 4) {
						if ((p.getX() == X + 3 && p.getY() == Y && !p.AraxxorEggBurst)
								|| (p.getX() == X + 2 && p.getY() == Y && !p.AraxxorEggBurst)
								|| (p.getX() == X + 2 && p.getY() == Y + 1 && !p.AraxxorEggBurst)) {
							p.eggBurst(X, Y, p.getPlane());
							World.removeObject(One);
							World.removeObject(Two);
							World.removeObject(Three);
							stop();
							return;
						}
					}

				}
			}, 0, 0);
			;
		}
		// return Utils.random(20, 21);
		return 21;
	}

	@Override
	public boolean canAttack(Araxxor npc, Player victim) {
		return victim.getY() > npc.getBase().getY() + 10;
	}

}
