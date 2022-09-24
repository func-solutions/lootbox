package me.func.ebisu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.cristalix.core.CoreApi;
import ru.cristalix.core.coupons.CouponsService;
import ru.cristalix.core.coupons.ICouponsService;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.invoice.IInvoiceService;
import ru.cristalix.core.invoice.InvoiceService;
import ru.cristalix.core.microservice.MicroServicePlatform;
import ru.cristalix.core.microservice.MicroserviceBootstrap;
import ru.cristalix.core.network.ISocketClient;

@Profile("prod")
@Configuration
public class MicroService {

	@Bean
	public ISocketClient run() {

		MicroserviceBootstrap.bootstrap(new MicroServicePlatform(2));
		return ISocketClient.get();
	}

	@Bean
	public CoreApi getCoreApi() {
		return CoreApi.get();
	}

	@Bean
	public GlobalCommandManager globalCommandManager(ISocketClient socketClient) {

		GlobalCommandManager commandManager = new GlobalCommandManager(socketClient, "ebisu");
		commandManager.start();
		return commandManager;
	}

	@Bean
	public IInvoiceService invoiceService(CoreApi coreApi, ISocketClient socketClient) {

		coreApi.registerService(IInvoiceService.class, new InvoiceService(socketClient));
		return IInvoiceService.get();
	}

	@Bean
	public ICouponsService couponsService(CoreApi coreApi, ISocketClient socketClient) {

		coreApi.registerService(ICouponsService.class, new CouponsService(socketClient));
		return ICouponsService.get();
	}

}
