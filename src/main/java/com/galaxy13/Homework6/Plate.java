package com.galaxy13.Homework6;

import java.util.LinkedList;
import java.util.List;

public class Plate {
    int maxFoodQuantity;
    int currentFoodQuantity = 0;
    List<Food> foods = new LinkedList<>();

    public Plate(int maxFoodQuantity) {
        this.maxFoodQuantity = maxFoodQuantity;
    }

    public boolean addFood(Food food) {
        if (food.getFoodQuantity() + currentFoodQuantity > maxFoodQuantity) {
            System.out.printf("%s food can't be placed to this plate%n", food.getFoodName());
            return false;
        } else {
            this.currentFoodQuantity += food.getFoodQuantity();
            foods.add(food);
            System.out.printf("%s added to the plate%n", food.getFoodName());
            return true;
        }
    }

    public void addFood(Food[] foods) {
        for (Food food : foods) {
            if (!addFood(food)) {
                System.out.println("Food has been partially added to plate.");
                return;
            }
        }
        System.out.println("All food successfully added to plate");
    }

    public boolean removeFood(int removeQuantity) {
        if (removeQuantity > currentFoodQuantity) {
            return false;
        } else {
            int currentRemoveQuantity = removeQuantity;
            for (int i = 0; i < foods.size(); i++) {
                Food food = this.foods.removeLast();
                if (food.getFoodQuantity() > currentRemoveQuantity) {
                    food.setFoodQuantity(food.getFoodQuantity() - currentRemoveQuantity);
                    currentFoodQuantity -= currentRemoveQuantity;
                    foods.add(food);
                    break;
                } else {
                    currentRemoveQuantity -= food.getFoodQuantity();
                    currentFoodQuantity -= food.getFoodQuantity();
                }
            }
        }
        return true;
    }
}
