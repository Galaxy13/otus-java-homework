package com.galaxy13.Homework5;

import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class Animal {

    private String name;
    private double runningVelocity;
    private double swimmingVelocity;
    private double endurance;
    private int enduranceSwimmingCost;

    private String getName() {
        String fullName = this.getClass().getName();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }

    public double run(double distance) {
        endurance -= distance;
        return exhaustionCheck(distance, runningVelocity, 1);
    }

    public double swim(double distance) {
        endurance -= distance * enduranceSwimmingCost;
        return exhaustionCheck(distance, swimmingVelocity, enduranceSwimmingCost);
    }

    private double exhaustionCheck(double distance, double velocity, int enduranceCost) {
        if (endurance < 0) {
            System.out.printf("%s is exhausted. %s meters not finished%n", getName(), -endurance / enduranceCost);
            endurance = 0;
            return -1;
        } else {
            System.out.printf("%s successfully finished. Endurance left %s%n", getName(), endurance / enduranceCost);
            return velocity * distance;
        }
    }

    public void info() {
        System.out.printf("%nAnimal type: %s%n", getName());
        System.out.printf("Name: %s%n", name);
        System.out.printf("Running velocity: %s m/s%n", runningVelocity);
        System.out.printf("Swimming velocity: %s m/s %n", swimmingVelocity);
        System.out.printf("Endurance left: %s%n%n", endurance);
    }
}
