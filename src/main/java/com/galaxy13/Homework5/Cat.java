package com.galaxy13.Homework5;

import lombok.experimental.Delegate;

public class Cat implements Runnable {
    @Delegate(excludes = Swimmable.class)
    private final Animal animal;

    public Cat(String name, double runningVelocity, double swimmingVelocity, double endurance) {
        animal = new Animal(name, runningVelocity, swimmingVelocity, endurance, 2, getClass().getSimpleName());
    }

    @Override
    public double run(double distance) {
        return animal.run(distance);
    }
}
