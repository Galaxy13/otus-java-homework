package com.galaxy13;

import com.galaxy13.Homework5.Cat;
import com.galaxy13.Homework5.Dog;
import com.galaxy13.Homework5.Horse;

public class Main {
    private static void printTime(double time) {
        if (time != -1) {
            System.out.printf("Time spent: %s s%n", time);
        }
    }
    public static void main(String[] args) {
        Horse horse = new Horse("Pony", 10.0, 2.0, 50);
        printTime(horse.run(10));
        printTime(horse.swim(30));
        horse.info();

        Cat cat = new Cat("Walky", 5.0, 0.0, 100);
        printTime(cat.run(90));
        // printTime(cat.swim(120)); now there is no function swim() available for Cat
        cat.info();

        Dog dog = new Dog("Bobik", 20.0, 5.0, 150);
        printTime(dog.run(10));
        printTime(dog.swim(50));
        dog.info();
    }
}