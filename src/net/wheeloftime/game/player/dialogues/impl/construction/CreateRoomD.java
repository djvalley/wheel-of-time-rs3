package net.wheeloftime.game.player.dialogues.impl.construction;

import net.wheeloftime.game.player.actions.skills.construction.House.RoomReference;
import net.wheeloftime.game.player.dialogues.Dialogue;

public class CreateRoomD extends Dialogue {

	private RoomReference room;

	@Override
	public void start() {
		this.room = (RoomReference) parameters[0];
		sendPreview();
	}

	public void sendPreview() {
		sendOptionsDialogue("Select an Option", "Rotate clockwise",
				"Rotate anticlockwise.", "Build.", "Cancel");
		player.getHouse().previewRoom(room, false);
	}

	@Override
	public void run(int interfaceId, int componentId, int slotId) {
		if (componentId == OPTION_4) {
			end();
			return;
		}
		if (componentId == OPTION_3) {
			end();
			player.getHouse().createRoom(room);
			return;
		}
		player.getHouse().previewRoom(room, true);
		room.setRotation((room.getRotation() + (componentId == OPTION_1 ? 1
				: -1)) & 0x3);
		sendPreview();
	}

	@Override
	public void finish() {
		player.getHouse().previewRoom(room, true);
	}

}
