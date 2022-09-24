package me.func.ebisu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.PackCaseRelationRepository;
import org.springframework.stereotype.Component;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.invoice.IInvoiceService;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuyCommandController {

	private final GlobalCommandManager commandManager;
	private final IInvoiceService invoiceService;
	private final PackCaseRelationRepository relationRepository;
	private final BoxRepository boxRepository;

	@PostConstruct
	public void run() {
		commandManager.registerCommand("box-buy", execution -> {

			log.info("Get buy request from {}.", execution.getPlayer());

			invoiceService.getBalanceData(execution.getPlayer()).thenAccept(data -> {
				log.info("Summa: " + data.getCrystals() + data.getCoins());

				val box = boxRepository.findById(Long.parseLong(execution.getArgs()[0])).get();
				val drop = relationRepository.randomRalation(box).get();

				log.info("Drop: " + drop);
			});
		});
	}
}
