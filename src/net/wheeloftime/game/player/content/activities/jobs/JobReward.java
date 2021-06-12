package net.wheeloftime.game.player.content.activities.jobs;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.Player;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class JobReward {

	private final RewardType type;
	private final Object[] data;

	public JobReward(RewardType type, int... data) {
		Object[] o = new Object[data.length];
		for (int i = 0; i < o.length; i++)
			o[i] = data[i];
		this.type = type;
		this.data = o;
	}

	public JobReward(RewardType type, Item item) {
		Object[] o = { item };
		this.type = type;
		this.data = o;
	}

	public JobReward(RewardType type, String item) {
		Object[] o = { item };
		this.type = type;
		this.data = o;
	}

	public JobReward(RewardType type, Object[]... data) {
		this.type = type;
		this.data = data;
	}

	public void reward(Player player) {

	}

	public RewardType getType() {
		return type;
	}

	public Object[] getData() {
		return data;
	}

}
