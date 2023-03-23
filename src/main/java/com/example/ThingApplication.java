package com.example;

import io.micronaut.runtime.Micronaut;

public class ThingApplication {

    public static void main(String[] args) {
        Micronaut.build(args)
                .mainClass(ThingApplication.class)
                .start();
    }
}