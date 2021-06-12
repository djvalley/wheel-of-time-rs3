package net.wheeloftime.game.player.actions.skills.dungeoneering.rooms;

import net.wheeloftime.game.player.actions.skills.dungeoneering.DungeonManager;
import net.wheeloftime.game.player.actions.skills.dungeoneering.RoomReference;

public interface RoomEvent {

	public void openRoom(DungeonManager dungeon, RoomReference reference);
}
