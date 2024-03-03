package com.galaxy13;

import com.galaxy13.Homework6.Cat;
import com.galaxy13.Homework6.Food;
import com.galaxy13.Homework6.Plate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Cat> catList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            catList.add(new Cat(random.nextInt(10, 30)));
        }
        Plate plate = new Plate(120);
        Food[] foods = {new Food("Beaf", 30), new Food("Raw Chicken", 20), new Food("Dry cat food", 50), new Food("Duck", 40)};
        plate.addFood(foods[1]);
        plate.addFood(foods);
        for (Cat cat : catList) {
            cat.eatFrom(plate);
        }
        for (int i = 0; i < catList.size(); i++) {
            System.out.printf("Cat %s well-fed: %s%n", i + 1, catList.get(i).getSatietyStatus());
        }
    }
}