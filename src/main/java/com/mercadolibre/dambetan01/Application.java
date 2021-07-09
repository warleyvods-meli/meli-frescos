package com.mercadolibre.dambetan01;

import com.mercadolibre.dambetan01.config.SpringConfig;
import com.mercadolibre.dambetan01.service.AccountService;
import com.mercadolibre.dambetan01.util.ScopeUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private final AccountService accountService;

	public Application(AccountService accountService) {
		this.accountService = accountService;
	}

	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);
	}

	@Bean
	InitializingBean sendDataBase() {
		return () -> {
			accountService.insertAdminUser();
			accountService.insertSellerUser();
			accountService.insertAgentUser();
			accountService.insertBuyerUser();
		};
	}

}
