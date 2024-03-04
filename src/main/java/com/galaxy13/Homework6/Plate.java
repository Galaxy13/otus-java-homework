package com.galaxy13.Homework6;

import java.util.LinkedList;

public class Plate {
    private final int maxFoodQuantity;
    private final LinkedList<Food> foods = new LinkedList<>();

    public Plate(int maxFoodQuantity) {
        this.maxFoodQuantity = maxFoodQuantity;
    }

    public boolean addFood(Food food) {
        if (food.getFoodQuantity() + foods.stream().map(Food::getFoodQuantity).reduce(0, Integer::sum) > maxFoodQuantity) {
            System.out.printf("%s food can't be placed to this plate%n", food.getName());
            return false;
        } else {
            foods.add(food);
            System.out.printf("%s added to the plate%n", food.getName());
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
        int currentFoodQuantity = foods.stream()
                .map(Food::getFoodQuantity)
                .reduce(0, Integer::sum);
        if (removeQuantity > currentFoodQuantity) {
            return false;
        } else {
            int currentRemoveQuantity = removeQuantity;
            for (int i = 0; i < foods.size(); i++) {
                Food food = foods.removeLast();
                if (food.getFoodQuantity() > currentRemoveQuantity) {
                    food.decreaseFoodQuantity(currentRemoveQuantity);
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
