package me.func.ebisu.entity;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface Giveaway {

	CompletableFuture<?> accept(UUID player, RewardEntity reward);

}
