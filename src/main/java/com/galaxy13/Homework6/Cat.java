package com.galaxy13.Homework6;

public class Cat {
    private final int requiredFoodQuantity;
    private boolean satiety = false;

    public Cat(int requiredFoodQuantity) {
        this.requiredFoodQuantity = requiredFoodQuantity;
    }

    public void eatFrom(Plate plate) {
        if (plate.removeFood(requiredFoodQuantity)) {
            this.satiety = true;
            System.out.println("Cat is now full and happy");
        } else {
            System.out.println("Not enough food for cat");
        }
    }

    public boolean getSatietyStatus() {
        return this.satiety;
    }
}
