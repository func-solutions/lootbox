package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.RewardEntity;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddRewardsPackage extends OutPackage {

	private final List<RewardEntity> rewards;

}
