package com.galaxy13.Homework9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Homework9 {
    public static List<Integer> range(int min, int max) {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            arrayList.add(i);
        }
        return arrayList;
    }

    public static int sumOfElementsBiggerFive(List<Integer> integerList) {
        int sumBiggerFive = 0;
        for (int number : integerList) {
            if (number > 5) {
                sumBiggerFive += 5;
            }
        }
        return sumBiggerFive;
    }

    public static void fillList(int value, List<Object> fillingList) {
        Collections.fill(fillingList, value);
    }

    public static void incrementValue(int value, List<Integer> integerList) {
        if (integerList == null) {
            System.out.println("Null pointer not accepted");
            return;
        }
        integerList.replaceAll(number -> number + value);
    }

    public static List<String> getNamesList(List<User> userList) {
        if (userList == null) {
            System.out.println("Null pointer not accepted");
            return null;
        }
        return userList.stream().map(User::getName).collect(Collectors.toList());
    }

    public static List<User> getUserOlder(List<User> userList, int minAge) {
        if (userList == null) {
            System.out.println("Null pointer not accepted");
            return null;
        }
        return userList.stream().filter(user -> user.getAge() >= minAge).collect(Collectors.toList());
    }

    public static boolean checkAvgAge(List<User> userList, int averageAge) {
        if (userList == null) {
            System.out.println("Null pointer not accepted");
            return false;
        }
        return userList.stream().map(User::getAge).reduce(Integer::sum).orElse(0) / (double) averageAge > averageAge;
    }

    public static User youngestUser(List<User> userList) {
        if (userList == null) {
            System.out.println("Null pointer not accepted");
            return null;
        }
        return userList.stream().min(User::compareTo).orElse(null);
    }
}
