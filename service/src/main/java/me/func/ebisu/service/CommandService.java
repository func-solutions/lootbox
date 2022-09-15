package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.model.MenuCommand;
import org.springframework.stereotype.Service;
import ru.cristalix.core.globalcommand.GlobalCommandManager;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandService {

	private final Map<String, MenuCommand> commandMap;

	private final GlobalCommandManager commandManager;

	@PostConstruct
	public void run() {

		commandMap.forEach(this::registerCachedMenuOpener);

		commandManager.registerCommand("box-buy", execution -> {

			log.info("Get buy request from {}.", execution.getPlayer());

		});
	}

	private void registerCachedMenuOpener(String command, MenuCommand menuCommand) {

		commandManager.registerCommand(command, execution -> {

			val response = menuCommand.getCachedResponse();
			log.info("Accept {} command from {}, response: {}", command, execution.getPlayer(), response);
			execution.sendPayload("storage:open-json", response);
		});
	}
}
