/*
 * Copyright (C) Tamedia AG 2021
 */

package com.example.client;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

import static com.example.client.ThingClient.THING_BASE_URI;


@Client(value = "${thing.host}", path = THING_BASE_URI)
public interface ThingClient {

    String THING_BASE_URI = "/thing";

    @SingleResult
    @Get("/{message}")
    Publisher<String> getMessage(String message);
}
