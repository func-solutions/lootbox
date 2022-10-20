package me.func.ebisu.configuration;

import me.func.ebisu.model.Giveaway;
import me.func.protocol.graffiti.packet.AddGraffitiPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pw.lach.p13n.network.tower.GiveModelToUserPackage;
import ru.cristalix.core.network.ISocketClient;

@Configuration
public class GiveawayStratagyConfiguration {

	@Bean("personalization")
	public Giveaway personalization(ISocketClient client) {
		return (player, reward) -> client.writeAndAwaitResponse(new GiveModelToUserPackage(player, reward.getRewardItemId()));
	}

	@Bean("graffiti")
	public Giveaway graffiti(ISocketClient client) {
		return (player, reward) -> client.writeAndAwaitResponse(new AddGraffitiPackage(player, reward.getRewardItemId(), 10));
	}

}
