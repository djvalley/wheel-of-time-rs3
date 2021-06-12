package net.wheeloftime.game.player.content.activities.minigames.ectofuntus;

import net.wheeloftime.game.Animation;
import net.wheeloftime.game.item.ItemIdentifiers;
import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.actions.Action;

public class SlimeBucketFill extends Action {

	/**
	 * @author: miles M
	 */

	public SlimeBucketFill() {

	}

	@Override
	public boolean start(Player player) {
		return true;
	}

	@Override
	public boolean process(Player player) {
		return player.getInventory().containsItem(ItemIdentifiers.EMPTY_BUCKET,
				1);
	}

	@Override
	public int processWithDelay(Player player) {
		if (fillBucket(player)) {
			return 1;
		}
		return 1;
	}

	@Override
	public void stop(Player player) {

	}

	private boolean fillBucket(Player player) {
		if (player.getInventory().containsItem(ItemIdentifiers.EMPTY_BUCKET, 1)) {
			player.setNextAnimation(new Animation(4471));
			player.getInventory().deleteItem(ItemIdentifiers.EMPTY_BUCKET, 1);
			player.getInventory().addItem(ItemIdentifiers.BUCKET_OF_SLIME, 1);
			return true;
		}
		return false;
	}
}