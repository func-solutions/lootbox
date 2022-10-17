package me.func.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cristalix.core.CoreApi;
import ru.cristalix.core.coupons.CouponsService;
import ru.cristalix.core.coupons.ICouponsService;
import ru.cristalix.core.globalcommand.GlobalCommandManager;
import ru.cristalix.core.invoice.IInvoiceService;
import ru.cristalix.core.invoice.InvoiceService;
import ru.cristalix.core.network.ISocketClient;

@Configuration
public class MockedMicroService {

	@Bean
	public ISocketClient run() {
		return Mockito.mock(ISocketClient.class);
	}

	@Bean
	public CoreApi getCoreApi() {
		return CoreApi.get();
	}


	@Bean
	public GlobalCommandManager globalCommandManager(ISocketClient socketClient) {
		return new GlobalCommandManager(socketClient, "ebisu");
	}

	@Bean
	public IInvoiceService invoiceService(ISocketClient socketClient) {
		return new InvoiceService(socketClient);
	}

	@Bean
	public ICouponsService couponsService(ISocketClient socketClient) {
		return new CouponsService(socketClient);
	}

}
