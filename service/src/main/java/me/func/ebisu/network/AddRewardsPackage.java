package me.func.ebisu.network;

import me.func.ebisu.entity.RewardEntity;

import java.util.List;

public record AddRewardsPackage(List<RewardEntity> rewards) {
}
