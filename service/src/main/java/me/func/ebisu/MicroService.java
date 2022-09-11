package me.func.ebisu;

import lombok.RequiredArgsConstructor;
import me.func.ebisu.service.user.DefaultCommandService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.cristalix.core.microservice.MicroServicePlatform;
import ru.cristalix.core.microservice.MicroserviceBootstrap;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Component
@RequiredArgsConstructor
public class MicroService {

	private final DefaultCommandService defaultCommandService;

	@EventListener(ContextRefreshedEvent.class)
	public void run() {
		MicroserviceBootstrap.bootstrap(new MicroServicePlatform(2));
		defaultCommandService.run();
	}

}
