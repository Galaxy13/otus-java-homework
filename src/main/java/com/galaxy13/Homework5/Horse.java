package com.galaxy13.Homework5;

import lombok.experimental.Delegate;

public class Horse implements Runnable, Swimmable {
    @Delegate
    private final Animal animal;

    public Horse(String name, double runningVelocity, double swimmingVelocity, double endurance) {
        animal = new Animal(name, runningVelocity, swimmingVelocity, endurance, 2, getClass().getSimpleName());
    }

    @Override
    public double run(double distance) {
        return animal.run(distance);
    }

    @Override
    public double swim(double distance) {
        return animal.swim(distance);
    }
}
