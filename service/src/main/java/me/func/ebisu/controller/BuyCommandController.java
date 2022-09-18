package me.func.ebisu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.invoice.InvoiceService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class BuyCommandController {

	private final GlobalCommandManager commandManager;
	private final InvoiceService invoiceService;

	@PostConstruct
	public void run() {
		commandManager.registerCommand("box-buy", execution -> {

			log.info("Get buy request from {}.", execution.getPlayer());
			System.out.println(Arrays.toString(execution.getArgs()));

			invoiceService.getBalanceData(execution.getPlayer()).thenAccept(data -> {
				log.info("Summa: " + data.getCrystals() + data.getCoins());
			});
		});
	}
}
