/*
 * Copyright (C) Tamedia AG 2022
 */

package com.example.service;


import com.example.commons.AbstractTest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static com.example.client.ThingClient.THING_BASE_URI;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;


@MicronautTest(transactional = false)
class ThingServiceTest extends AbstractTest {

    @Inject
    private ThingService service;


    @Test
    void create_should_be_ok() {
        //when
        String message = "message text";
        service.create(message).block();
    }

    @Test
    void createWithRestCall_should_fail() {
        //given
        String message = "message";
        wireMockServer.stubFor(com.github.tomakehurst.wiremock.client.WireMock.get(urlEqualTo(THING_BASE_URI + "/" + message))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Message response")));
        //when
        service.createWithRestCall(message).block();
    }

}
