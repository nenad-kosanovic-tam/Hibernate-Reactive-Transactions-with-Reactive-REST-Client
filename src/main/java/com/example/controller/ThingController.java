package com.example.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

@Controller(ThingController.THING_BASE_URI)
public class ThingController {

    public static final String THING_BASE_URI = "/thing";

    @Get("/{message}")
    public Mono<String> getMessage(@PathVariable String message) {
        return Mono.just(message + LocalTime.now().toString());
    }
}
