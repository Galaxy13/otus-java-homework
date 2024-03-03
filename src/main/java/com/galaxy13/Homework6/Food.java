package com.galaxy13.Homework6;

public class Food {
    private final String foodName;
    private int foodQuantity;

    public Food(String foodName, int foodQuantity) {
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
    }

    public int getFoodQuantity() {
        return this.foodQuantity;
    }

    public void setFoodQuantity(int newFoodQuantity) {
        this.foodQuantity = newFoodQuantity;
    }

    public String getFoodName() {
        return this.foodName;
    }
}
