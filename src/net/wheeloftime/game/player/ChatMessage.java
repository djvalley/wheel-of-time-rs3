package net.wheeloftime.game.player;

import net.wheeloftime.utils.Censor;
import net.wheeloftime.utils.Utils;

public class ChatMessage {

	private String message;
	private String filteredMessage;

	public ChatMessage(String message) {
		if (!(this instanceof QuickChatMessage)) {
			filteredMessage = Censor.getFilteredMessage(message);
			this.message = Utils.fixChatMessage(message);
		} else
			this.message = message;
	}

	public String getMessage(boolean filtered) {
		return filtered ? filteredMessage : message;
	}
}
