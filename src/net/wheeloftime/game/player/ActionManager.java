package net.wheeloftime.game.player;

import net.wheeloftime.game.player.actions.Action;

public final class ActionManager {

	private Player player;
	private Action action;
	private int actionDelay;

	ActionManager(Player player) {
		this.player = player;
	}

	void process() {
		if (action != null && !action.process(player))
			forceStop();
		if (actionDelay > 0) {
			actionDelay--;
			return;
		}
		if (action == null)
			return;
		int delay = action.processWithDelay(player);
		if (delay == -1) {
			forceStop();
			return;
		}
		actionDelay += delay;
	}

	public boolean setAction(Action skill) {
		forceStop();
		if (!skill.start(player))
			return false;
		this.action = skill;
		return true;
	}

	public void forceStop() {
		if (action == null)
			return;
		action.stop(player);
		action = null;
	}

	public int getActionDelay() {
		return actionDelay;
	}

	public void addActionDelay(int skillDelay) {
		this.actionDelay += skillDelay;
	}

	public void setActionDelay(int skillDelay) {
		this.actionDelay = skillDelay;
	}

	public boolean hasSkillWorking() {
		return action != null;
	}

	public Action getAction() {
		return action;
	}
}