package me.func.ebisu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.entity.Giveaway;
import me.func.ebisu.entity.PackEntity;
import me.func.ebisu.entity.RewardEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.cristalix.core.invoice.BalanceData;
import ru.cristalix.core.invoice.IInvoiceService;
import ru.cristalix.core.invoice.Invoice;
import ru.cristalix.core.invoice.InvoiceResult;

import java.util.ArrayList;
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

		log.info("Give rewards to {}, pack: {}", player, pack);

		return CompletableFuture.allOf(pack.getRewards().stream().map(reward -> {

			val giveaway = stratagies.get(reward.getType().name().toLowerCase(Locale.ROOT));

			return giveaway == null ? CompletableFuture.completedFuture(null) : giveaway.accept(player, reward);

		}).toArray(CompletableFuture[]::new));
	}

	public CompletableFuture<InvoiceResult> bill(UUID player, Invoice invoice) {

		log.info("Accept invoice bill from {}, data {}", player, invoice);

		return invoiceService.bill(player, invoice);
	}

	public CompletableFuture<Boolean> hasMoney(UUID player, int money) {

		return invoiceService.getBalanceData(player).thenApply(data -> data.getTotalCrystals() >= money);
	}

}
