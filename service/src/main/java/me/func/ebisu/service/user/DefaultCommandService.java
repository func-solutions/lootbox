package me.func.ebisu.service.user;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.service.report.ReportService;
import me.func.protocol.menu.SelectionModel;
import org.springframework.stereotype.Component;
import ru.cristalix.core.GlobalSerializers;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.network.ISocketClient;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author func 08.09.2022
 * @project ebisu
 */
@Component
@RequiredArgsConstructor
public class DefaultCommandService {

	private final ReportService reportService;
	private final Cache<String, String> cache = Caffeine.newBuilder()
			.expireAfterWrite(1, TimeUnit.MINUTES)
			.maximumSize(5)
			.build();

	public void run() {
		GlobalCommandManager manager = new GlobalCommandManager(ISocketClient.get(), "ebisu");

		List.of("box-list", "box-logs", "box-rewards").forEach(command -> registerCachedMenuOpener(manager, command));

		manager.start();
	}

	private void registerCachedMenuOpener(GlobalCommandManager manager, String command) {
		manager.registerCommand(command, execution -> execution.sendPayload("storage:open-json", cache.get(command, key -> {
			SelectionModel data = switch (key) {
				case "box-list" -> reportService.getList();
				case "box-logs" -> reportService.getLogs();
				case "box-rewards" -> reportService.getRewards();
				default -> throw new IllegalStateException("Unexpected value: " + key);
			};
			return GlobalSerializers.toJson(data);
		})));
	}
}
