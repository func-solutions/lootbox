package me.func.ebisu.configuration;

import me.func.ebisu.entity.Giveaway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
public class GiveawayStratagyConfiguration {

	@Bean("personalization")
	public Giveaway personalization() {
		return (player, reward) -> CompletableFuture.completedFuture(null);
	}

	@Bean("graffiti")
	public Giveaway graffiti() {
		return (player, reward) -> CompletableFuture.completedFuture(null);
	}

}
