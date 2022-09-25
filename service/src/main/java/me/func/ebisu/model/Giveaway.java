package me.func.ebisu.model;

import me.func.ebisu.entity.RewardEntity;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface Giveaway {

	CompletableFuture<?> accept(UUID player, RewardEntity reward);

}
