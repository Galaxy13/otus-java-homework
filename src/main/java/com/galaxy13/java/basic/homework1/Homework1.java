package com.galaxy13.java.basic.homework1;

import java.util.Random;
import java.util.Scanner;

public class Homework1 {
    private static void greetings(){
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    private static void checkSign(int a, int b, int c){
        if (a + b + c > 0){
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    private static void selectColor(){
        int data = 15;
        if (data <= 10){
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    private static void compareNumbers(){
        int a = 0;
        int b = 10;
        if (a >= b){
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    private static void addOrSubtractAndPrint(int initValue, int delta, boolean increment){
        if (increment){
            System.out.println(initValue + delta);
        } else {
            System.out.println(initValue - delta);
        }
    }

    public static void main(String [] args){
        Homework1.greetings();
        Homework1.checkSign(1, 2, 3);
        Homework1.checkSign(-1, 0, -1);
        Homework1.selectColor();
        Homework1.compareNumbers();
        Homework1.addOrSubtractAndPrint(2, 3, true);
        Homework1.addOrSubtractAndPrint(2, 3, false);
        System.out.println("\n");

        // Task with *
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command number: ");
        Random random = new Random();
        int commandNumber;
        while (true){
            if (scanner.hasNextInt()){
                int scannedNumber = scanner.nextInt();
                if (1 <= scannedNumber && scannedNumber <= 5){
                    commandNumber = scannedNumber;
                    break;
                }
                else {
                    System.out.println("Not a valid number: Please enter number in range 1-5: ");
                }
            } else {
                System.out.println("Not a valid number or literal entered. Please enter the number: ");
                scanner.nextLine();
            }
        }
        switch (commandNumber){
            case 1: {
                Homework1.greetings();
                break;
            }
            case 2: {
                int a = random.nextInt();
                int b = random.nextInt();
                int c = random.nextInt();
                Homework1.checkSign(a, b, c);
                break;
            }
            case 3: {
                Homework1.selectColor();
                break;
            }
            case 4:{
                Homework1.compareNumbers();
                break;
            }
            case 5:{
                int initValue = random.nextInt();
                int delta = random.nextInt();
                boolean increment = random.nextInt(2) < 1;
                Homework1.addOrSubtractAndPrint(initValue, delta, increment);
                break;
            }
        }
    }
}
