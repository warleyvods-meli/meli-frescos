package com.mercadolibre.dambetan01.integration;

import com.mercadolibre.dambetan01.Application;
import com.mercadolibre.restclient.mock.RequestMockHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "SCOPE_SUFFIX = integration_test" })
public abstract class IntegrationTest {

    @AfterEach
    protected void afterEach() {
        RequestMockHolder.clear();
    }
}
