package me.func.ebisu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.service.LootboxService;
import me.func.ebisu.service.PlayerService;
import org.springframework.stereotype.Component;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.invoice.Invoice;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuyCommandController {

	private final GlobalCommandManager commandManager;
	private final PlayerService playerService;
	private final LootboxService lootboxService;
	private final BoxRepository boxRepository;

	@PostConstruct
	public void run() {

		commandManager.registerCommand("box-buy", execution -> {

			log.info("Get buy request from {}.", execution.getPlayer());

			val player = execution.getPlayer();
			val box = boxRepository.findById(Long.parseLong(execution.getArgs()[0])).get();

			// todo: сделать нормально
			playerService.hasMoney(player, box.getPrice().intValue()).thenAccept(has -> {
				if (has) {
					val drop = lootboxService.roll(execution.getPlayer(), box);

					playerService.bill(player, Invoice.builder().build()).thenAccept(result -> {
						playerService.giveReward(execution.getPlayer(), drop).thenAccept(lol -> {
							log.info("done");
						});
					});
				}
			});
		});
	}
}
