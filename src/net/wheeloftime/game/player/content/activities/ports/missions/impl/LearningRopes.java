package net.wheeloftime.game.player.content.activities.ports.missions.impl;

import net.wheeloftime.game.player.Player;
import net.wheeloftime.game.player.Skills;
import net.wheeloftime.game.player.content.activities.ports.missions.Mission;
import net.wheeloftime.game.player.content.activities.ports.missions.MissionReward;
import net.wheeloftime.game.player.content.activities.ports.missions.MissionConstants.MissionType;
import net.wheeloftime.game.player.content.activities.ports.missions.MissionConstants.RewardType;

/**
 * 
 * @author Frostbite<Abstract>
 * @contact<skype;frostbitersps><email;frostbitersps@gmail.com>
 */

public class LearningRopes extends Mission {

	protected Player player;

	public LearningRopes(Player player) {
		super(player);
	}

	@Override
	public String getMissionName() {
		return "Learning the ropes";
	}

	@Override
	public int getTotalLevelRequirement() {
		return 1125;
	}

	@Override
	public MissionType getMissionType() {
		return MissionType.DAILY;
	}

	@Override
	public int getExperienceRateMultiplier() {
		return 3;
	}

	@Override
	public MissionReward[] getPossibleRewards() {
		return new MissionReward[] { new MissionReward(RewardType.EXPERIENCE,
				Skills.FISHING, 1231) };
	}

}
