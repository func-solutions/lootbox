package me.func.ebisu;

import lombok.val;
import me.func.ebisu.service.user.DefaultCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.cristalix.core.*;
import ru.cristalix.core.command.CommandService;
import ru.cristalix.core.command.ICommandService;
import ru.cristalix.core.microservice.MicroServicePlatform;
import ru.cristalix.core.microservice.MicroserviceBootstrap;
import ru.cristalix.core.network.Capability;
import ru.cristalix.core.network.ISocketClient;
import ru.cristalix.core.network.PacketNameHelper;
import ru.cristalix.core.network.SocketClient;
import ru.cristalix.core.network.packages.ProxyCommandRunPackage;
import ru.cristalix.core.permissions.IPermissionService;
import ru.cristalix.core.permissions.PermissionService;
import ru.cristalix.core.realm.IRealmService;
import ru.cristalix.core.realm.RealmKind;
import ru.cristalix.core.realm.RealmService;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Component
public class MicroService {

	private final DefaultCommandService defaultCommandService;

	public MicroService(@Autowired DefaultCommandService defaultCommandService) {
		this.defaultCommandService = defaultCommandService;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void run() {
		MicroserviceBootstrap.bootstrap(new MicroServicePlatform(2));
		defaultCommandService.run();
	}

}
