package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.model.Giveaway;
import me.func.ebisu.entity.PackEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.cristalix.core.invoice.IInvoiceService;
import ru.cristalix.core.invoice.Invoice;
import ru.cristalix.core.invoice.InvoiceResult;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Profile("prod")
@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerService {

	private final IInvoiceService invoiceService;
	private final Map<String, Giveaway> stratagies;

	public CompletableFuture<Void> giveReward(UUID player, PackEntity pack) {

		// todo: тут вызывается, дальше в getRewards пустота

		return CompletableFuture.allOf(pack.getRewards().stream().map(reward -> {

			val stratagy = reward.getType().name().toLowerCase(Locale.ROOT);
			val giveaway = stratagies.get(stratagy);

			log.info("Give reward to {}, pack {}, stratagy {}", player, pack, stratagy);

			return giveaway == null ? CompletableFuture.completedFuture(null) : giveaway.accept(player, reward);

		}).toArray(CompletableFuture[]::new));
	}

	public CompletableFuture<InvoiceResult> bill(UUID player, Invoice invoice) {

		log.info("Accept invoice bill from {}, data {}", player, invoice);

		return invoiceService.bill(player, invoice);
	}

	public CompletableFuture<Integer> getBalance(UUID player) {

		log.info("Request money data from {}", player);

		return invoiceService.getBalanceData(player).thenApply(data -> data.getCrystals() + data.getCoins());
	}

}
