package me.func.ebisu.service.user;

import lombok.val;
import me.func.protocol.menu.Button;
import me.func.protocol.menu.SelectionModel;
import org.springframework.stereotype.Component;
import ru.cristalix.core.GlobalSerializers;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.network.ISocketClient;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author func 08.09.2022
 * @project ebisu
 */
@Component
public class DefaultCommandService {

	public void run() {
		// Меню со списком кейсов
		SelectionModel boxes = SelectionModel.builder()
				.title("Список сундуков")
				.columns(1)
				.rows(3)
				.storage(
						Button.builder()
								.command("box-buy")
								.title("Кейс 1")
								.price(99)
								.texture("minecraft:mcpatcher/cit/others/hub/quest_month.png")
								.build(),
						Button.builder()
								.command("box-buy")
								.title("Кейс 2")
								.price(19)
								.texture("minecraft:mcpatcher/cit/others/hub/quest_month.png")
								.build(),
						Button.builder()
								.command("box-buy")
								.title("Кейс 3")
								.price(22)
								.texture("minecraft:mcpatcher/cit/others/hub/quest_month.png")
								.build()
				).build();

		// Меню с логами
		val logsData = IntStream.range(1, 200).mapToObj(it -> Button.builder()
						.title("Лог " + it)
						.texture("minecraft:mcpatcher/cit/others/hub/quest_month.png")
						.build())
				.collect(Collectors.toList());
		SelectionModel logs = SelectionModel.builder()
				.title("Привет")
				.columns(1)
				.rows(9)
				.storage(logsData)
				.build();

		// Отправляем меню с кейсами
		GlobalCommandManager manager = new GlobalCommandManager(ISocketClient.get(), "ebisu");
		manager.registerCommand(
				"box-list",
				execution -> execution.sendPayload("storage:open-json", GlobalSerializers.toJson(boxes))
		);
		manager.registerCommand(
				"box-logs",
				execution -> execution.sendPayload("storage:open-json", GlobalSerializers.toJson(logs))
		);
		manager.registerCommand(
				"box-rewards",
				execution -> execution.sendPayload("storage:open-json", GlobalSerializers.toJson(logs))
		);
		manager.start();
	}
}
