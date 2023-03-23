/*
 * Copyright (C) Tamedia AG 2022
 */

package com.example.repository;

import com.example.model.entity.Thing;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;

import java.util.UUID;

@Repository
public interface ThingRepository extends ReactorCrudRepository<Thing, UUID> {
}
