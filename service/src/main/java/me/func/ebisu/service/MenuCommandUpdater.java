package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.func.ebisu.model.MenuCommand;
import me.func.protocol.menu.Button;
import me.func.protocol.menu.SelectionModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.cristalix.core.GlobalSerializers;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuCommandUpdater {

	private final List<MenuCommand> commands;

	@PostConstruct
	@Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
	public void update() {

		log.info("Update menu command data.");

		for (MenuCommand command : commands) {
			List<Button> buttons = command.getButtonSupplier().get();

			SelectionModel model = command.getModel();
			model.setData(buttons);
			command.setCachedResponse(GlobalSerializers.toJson(model));
		}
	}

}
