package com.galaxy13.Homework5;

public class Cat extends Animal {
    public Cat(String name, double runningVelocity, double swimmingVelocity, double endurance) {
        super(name, runningVelocity, swimmingVelocity, endurance, 0);
    }

    @Override
    public double swim(double distance) {
        System.out.println("Cat can't swim");
        return -1;
    }
}
