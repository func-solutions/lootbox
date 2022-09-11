package me.func.ebisu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.cristalix.core.microservice.MicroServicePlatform;
import ru.cristalix.core.microservice.MicroserviceBootstrap;

import javax.annotation.PostConstruct;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Component
@RequiredArgsConstructor
public class MicroService {

	@PostConstruct
	public void run() {
		MicroserviceBootstrap.bootstrap(new MicroServicePlatform(2));
	}

}
