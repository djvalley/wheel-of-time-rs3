package net.wheeloftime.game.player.content.activities.minigames.stealingcreation;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.ForceMovement;
import net.wheeloftime.game.WorldObject;
import net.wheeloftime.game.WorldTile;
import net.wheeloftime.game.item.FloorItem;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.controllers.Controller;
import net.wheeloftime.game.tasks.WorldTask;
import net.wheeloftime.game.tasks.WorldTasksManager;

public class StealingCreationLobbyController extends Controller {

	@Override
	public void start() {
		sendInterfaces();
	}

	@Override
	public void sendInterfaces() {
		player.getInterfaceManager().sendMinigameInterface(804);
		StealingCreationLobby.updateTeamInterface(player, false);
	}

	@Override
	public boolean canTakeItem(FloorItem item) {
		return false;
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (object.getId() == 39508 || object.getId() == 39509) {
			player.getControllerManager().forceStop();
			climOverStile(player, object, false);
			return false;
		}
		return true;
	}

	public static void climOverStile(final Player player,
			final WorldObject object, final boolean enterance) {
		player.lock(3);
		if (enterance) {
			if (!StealingCreationLobby.enterTeamLobby(player,
					object.getId() == 39509))
				return;
		} else
			StealingCreationLobby.removePlayer(player);
		player.setNextAnimation(new Animation(1560));
		final WorldTile toTile = new WorldTile(enterance ? object.getX()
				: object.getX() + 2, object.getY(), object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2,
				enterance ? ForceMovement.WEST : ForceMovement.EAST));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
			}
		}, 1);
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getDialogueManager()
				.startDialogue("SimpleMessage",
						"A magical force prevents you from teleporting from the arena.");
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getDialogueManager()
				.startDialogue("SimpleMessage",
						"A magical force prevents you from teleporting from the arena.");
		return false;
	}

	@Override
	public void magicTeleported(int type) {
		StealingCreationLobby.removePlayer(player);
	}

	@Override
	public void forceClose() {
		player.getInterfaceManager().removeMinigameInterface();
		StealingCreationLobby.removePlayer(player);
	}

	@Override
	public boolean logout() {
		StealingCreationLobby.removePlayer(player);
		player.setLocation(Helper.EXIT);
		return true;
	}

	@Override
	public boolean login() {
		StealingCreationLobby.removePlayer(player);
		player.setLocation(Helper.EXIT);
		return true;
	}

}
