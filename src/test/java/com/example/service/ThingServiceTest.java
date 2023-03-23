/*
 * Copyright (C) Tamedia AG 2022
 */

package com.example.service;


import com.example.commons.AbstractTest;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


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
        //when
        String message = "message text";
        service.createWithRestCall(message).block();
    }

}
