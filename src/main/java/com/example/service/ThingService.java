/*
 * Copyright (C) Tamedia AG 2022
 */

package com.example.service;

import com.example.client.ThingClient;
import com.example.model.dto.ThingDto;
import com.example.model.entity.Thing;
import com.example.repository.ThingRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Singleton
public class ThingService {

    @Inject
    private ThingRepository repository;

    @Inject
    private ThingClient thingClient;

    @Transactional
    public Mono<ThingDto> create(String message) {
        Thing thing = new Thing();
        thing.setMessage(message);

        return repository.save(thing)
                .map(t -> {
                    ThingDto thingDto = new ThingDto();
                    thingDto.setId(t.getId());
                    thingDto.setMessage(t.getMessage());

                    return thingDto;
                });
    }

    @Transactional
    public Mono<ThingDto> createWithRestCall(String message) {
        Thing thing = new Thing();
        thing.setMessage(message);

        return repository.save(thing).zipWhen(this::getMessage,
                (thingResult, messageResult) -> {
                    ThingDto thingDto = new ThingDto();
                    thingDto.setId(thingResult.getId());
                    thingDto.setMessage(messageResult);

                    return thingDto;
                });
    }

    private Mono<String> getMessage(Thing thingDto) {
        return Mono.from(thingClient.getMessage(thingDto.getMessage()));
    }
}
