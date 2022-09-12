package me.func.ebisu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cristalix.core.CoreApi;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.microservice.MicroServicePlatform;
import ru.cristalix.core.microservice.MicroserviceBootstrap;
import ru.cristalix.core.network.ISocketClient;

@Configuration
public class MicroService {

	@Bean
	public ISocketClient run() {

		MicroserviceBootstrap.bootstrap(new MicroServicePlatform(2));
		return ISocketClient.get();
	}

	@Bean
	public CoreApi getCoreApi() {
		return CoreApi.get();
	}

	@Bean
	public GlobalCommandManager globalCommandManager() {

		GlobalCommandManager commandManager = new GlobalCommandManager(ISocketClient.get(), "ebisu");
		commandManager.start();
		return commandManager;
	}

}
