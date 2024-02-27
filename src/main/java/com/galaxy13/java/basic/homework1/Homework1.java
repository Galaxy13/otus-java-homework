package com.galaxy13.java.basic.homework1;

import java.util.Scanner;

public class Homework1 {
    public void greetings(){
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    public void checkSign(int a, int b, int c){
        if (a + b + c > 0){
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void selectColor(){
        Scanner scanner = new Scanner(System.in);
        int data = 0;
        while (scanner.hasNext()){
            if (scanner.hasNextInt()){
                data = scanner.nextInt();
                break;
            } else {
                System.out.println("Not a number");
            }
            scanner.nextLine();
        }
        if (data <= 10){
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }


}
