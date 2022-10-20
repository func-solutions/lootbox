package me.func.ebisu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.network.BroadcastMessagePackage;
import me.func.ebisu.network.PlayerDropPackage;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.service.LootboxService;
import me.func.ebisu.service.PlayerService;
import org.springframework.stereotype.Component;
import ru.cristalix.core.GlobalSerializers;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.invoice.Invoice;
import ru.cristalix.core.invoice.InvoiceResult;
import ru.cristalix.core.network.ISocketClient;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuyCommandController {

	private final GlobalCommandManager commandManager;
	private final PlayerService playerService;
	private final LootboxService lootboxService;
	private final BoxRepository boxRepository;
	private final ISocketClient socketClient;

	@PostConstruct
	public void run() {

		commandManager.registerCommand("box-buy", execution -> {

			log.info("Get buy request from {}.", execution.getPlayer());

			val boxOptional = boxRepository.findById(Long.parseLong(execution.getArgs()[0]));

			if (boxOptional.isEmpty()) {
				log.info("Box is null!");
				return;
			}

			val box = boxOptional.get();

			billing(execution, box, Invoice.builder()
					.description("Покупка лутбокса: " + box.getTitle())
					.price(box.getPrice().intValue())
					.build()
			);
		});
	}

	private void billing(GlobalCommandManager.ProxyPlayerSender execution, BoxEntity box, Invoice invoice) {

		playerService.bill(execution.getPlayer(), invoice).thenAccept(result -> accept(execution, box, result));
	}

	private void accept(GlobalCommandManager.ProxyPlayerSender execution, BoxEntity box, InvoiceResult result) {
		log.info("Accept invoice result from {}, error message: {}.", execution.getPlayer(), result.getErrorMessage());

		if (result.isSuccess()) {

			onSuccesess(execution, box);
		} else {
			execution.sendPayload("ebisu:error", "{\"message\":\"" + result.getErrorMessage() + "\"}");
		}
	}

	private void onSuccesess(GlobalCommandManager.ProxyPlayerSender execution, BoxEntity box) {

		val rolled = lootboxService.roll(execution.getPlayer(), box);
		val giveawayFuture = playerService.giveReward(execution.getPlayer(), rolled);

		giveawayFuture.thenAccept(unused -> {

			log.info("Drop given to {}.", execution.getPlayer());

			execution.sendPayload("func:close", "");
			execution.sendPayload("ebisu:fine", GlobalSerializers.toJson(rolled));

			socketClient.forward(execution.getServer(), new PlayerDropPackage(execution.getPlayer(), rolled));
			socketClient.write(new BroadcastMessagePackage( "{\"text\":\"Игрок выбил новый предмет!\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"suggest_command\", \"value\":\"/run\"}}"));
		});
	}
}
