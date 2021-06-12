package net.wheeloftime.game.npc.dungeonnering;

import java.util.List;

import net.wheeloftime.game.Entity;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.npc.Drop;
import net.wheeloftime.game.npc.Drops;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonConstants;
import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;
import net.wheeloftime.utils.NPCDrops;
import net.wheeloftime.utils.Utils;

@SuppressWarnings("serial")
public class DungeonBoss extends DungeonNPC {

	private RoomReference reference;

	public DungeonBoss(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference) {
		this(id, tile, manager, reference, 1);
	}

	public DungeonBoss(int id, WorldTile tile, DungeonManager manager,
			RoomReference reference, double multiplier) {
		super(id, tile, manager, multiplier);
		this.setReference(reference);
		setForceAgressive(true);
		setIntelligentRouteFinder(true);
		setLureDelay(0);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		getManager().openStairs(getReference());
	}

	@Override
	public void drop() {
		Drops drops = NPCDrops.getDrops(getId());
		if (drops == null)
			return;

		Drop[] dropsA = drops.getDrops(Drops.COMMON);
		if (dropsA == null)
			return;
		Drop drop;
		drop = getDrop(dropsA);
		List<Player> players = getManager().getParty().getTeam();
		if (drop == null || players.size() == 0)
			return;
		handleDrops(drop, players);
	}

	private Drop getDrop(Drop[] dropsA) {

		Drop drop;
		if (getManager().getParty().getSize() == DungeonConstants.LARGE_DUNGEON)
			drop = dropsA[Utils.random(100) < 90 ? dropsA.length - 1 : Utils
					.random(dropsA.length)];
		else if (getManager().getParty().getSize() == DungeonConstants.LARGE_DUNGEON)
			drop = dropsA[Utils.random(100) < 60 ? dropsA.length - 1 : Utils
					.random(dropsA.length)];
		else
			drop = dropsA[Utils.random(dropsA.length)];
		return drop;
	}

	private void handleDrops(Drop drop, List<Player> players) {

		Player luckyPlayer = players.get(Utils.random(players.size()));
		Item item = sendDrop(luckyPlayer, drop);
		luckyPlayer.getPackets().sendGameMessage(
				"You received: " + item.getAmount() + " " + item.getName()
						+ ".");
		for (Player p2 : players) {
			if (p2 == luckyPlayer)
				continue;
			p2.getPackets().sendGameMessage(
					luckyPlayer.getDisplayName() + " received: "
							+ item.getAmount() + " " + item.getName() + ".");
		}
	}

	@Override
	public Item sendDrop(Player player, Drop drop) {
		Item item = new Item(drop.getItemId());
		player.getInventory().addItemDrop(item.getId(), item.getAmount());
		return item;
	}

	@Override
	public boolean isPoisonImmune() {
		return true;
	}

	public RoomReference getReference() {
		return reference;
	}

	public void setReference(RoomReference reference) {
		this.reference = reference;
	}
}
