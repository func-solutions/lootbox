package me.func.ebisu.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.func.ebisu.data.Menu;
import me.func.ebisu.entity.BoxEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.network.ISocketClient;

import java.util.Arrays;

/**
 * @author func 08.09.2022
 * @project ebisu
 */
@Service
public class DefaultCommandService {

	public DefaultCommandService(@Autowired ObjectMapper objectMapper) {
		GlobalCommandManager manager = new GlobalCommandManager(ISocketClient.get(), "ebisu");

		// Тестовые данные
		Menu menu = new Menu("Кейсы", 3, 1, Arrays.asList(
				/*BoxEntity.builder()
						.price(99.0)
						.status("active")
						.title("Кейс фанка")
						.build(),
				BoxEntity.builder()
						.price(199.0)
						.status("active")
						.title("Кейс миши")
						.build(),
				BoxEntity.builder()
						.price(1.0)
						.status("active")
						.title("Кейс кирилла")
						.build()*/
		));

		// Отправляем меню с кейсами
		manager.registerCommand("lootbox:box", execution -> {
			execution.sendPayload("selection:json", objectMapper.writeValueAsString(menu));
		});
	}
}
