package com.galaxy13.Homework6;

public class Food {
    private final String name;
    private int foodQuantity;

    public Food(String foodName, int foodQuantity) {
        this.name = foodName;
        this.foodQuantity = foodQuantity;
    }

    public int getFoodQuantity() {
        return this.foodQuantity;
    }

    public void decreaseFoodQuantity(int foodQuantity) {
        this.foodQuantity -= foodQuantity;
    }

    public String getName() {
        return this.name;
    }
}
