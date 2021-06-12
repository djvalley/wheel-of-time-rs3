package net.wheeloftime.game.player.content.activities.jobs.impl.bananaplantation;

import java.io.Serializable;

import net.wheeloftime.game.item.Item;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.content.activities.jobs.Job;
import net.wheeloftime.game.player.content.activities.jobs.JobReward;
import net.wheeloftime.game.player.content.activities.jobs.RewardType;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class BananaPlantation extends Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8653661953154110854L;
	protected Player player;

	public BananaPlantation(Player player) {
		super(player);
	}

	@Override
	public JobReward[] getRewards() {
		return new JobReward[] { new JobReward(RewardType.ITEM, new Item(995,
				30)) };
	}
}
