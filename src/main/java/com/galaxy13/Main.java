package com.galaxy13;

import com.galaxy13.Homework9.Homework9;
import com.galaxy13.Homework9.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Homework9.range(1, 5));

        List<Integer> intList1 = List.of(1, 6, 10, 5, -1);
        System.out.println(Homework9.sumOfElementsBiggerFive(intList1));

        List<Object> testList2 = new ArrayList<>(List.of("well", 234, 56.0, List.of()));
        Homework9.fillList(23, testList2);
        System.out.println(testList2);

        List<Integer> testList3 = new ArrayList<>(List.of(1, 10, 100, 0));
        Homework9.incrementValue(2, testList3);
        System.out.println(testList3);

        List<User> testList4 = List.of(new User("John", 23), new User("Martha", 46), new User("Mary", 20), new User("Gennadiy", 24));
        System.out.println(Homework9.getNamesList(testList4));
        System.out.println(Homework9.getUserOlder(testList4, 24));
        System.out.println(Homework9.checkAvgAge(testList4, 33));
        System.out.println(Homework9.youngestUser(testList4));

        System.out.println(Homework9.getNamesList(null));
        System.out.println(Homework9.getNamesList(List.of()));
    }
}