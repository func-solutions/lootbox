package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import me.func.ebisu.configuration.MenuCommand;
import org.springframework.stereotype.Service;
import ru.cristalix.core.globalcommand.GlobalCommandManager;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommandService {

	private final Map<String, MenuCommand> commandMap;

	private final GlobalCommandManager commandManager;

	@PostConstruct
	public void run() {
		commandMap.forEach(this::registerCachedMenuOpener);
	}

	private void registerCachedMenuOpener(String command, MenuCommand menuCommand) {
		commandManager.registerCommand(command, execution -> execution.sendPayload("storage:open-json", menuCommand.getCachedResponse()));
	}
}
