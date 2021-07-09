package com.mercadolibre.dambetan01.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.mercadolibre.dambetan01.util.ScopeUtils.SCOPE_VALUE;
import static com.mercadolibre.dambetan01.util.ScopeUtils.isLocalScope;
import static java.lang.String.format;

@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("Meli Frescos") String appName, @Value("Meli Frescos API") String description, @Value("v1") String version) {

        //api.addServersItem(new Server().url(isLocalScope() ? "http://localhost:8080" : format("https://%s_%s.furyapps.io", SCOPE_VALUE, appName))
        //.description(format("Scope %s", SCOPE_VALUE)));
        return new OpenAPI().info(new Info().title(appName)
                .version(version)
                .description(description)
                .contact(new Contact().name("dambetan01")
                        .email("ext_dambetan@mercadolibre.com")));
    }
}
