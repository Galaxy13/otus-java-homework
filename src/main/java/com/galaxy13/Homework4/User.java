package com.galaxy13.Homework4;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    String surname;
    String name;
    String patronymic;
    int birthYear;
    String email;

    public void retrieveInfo(int currentYear) {
        if (currentYear - birthYear > 40) {
            System.out.printf("Full name: %s %s %s%n", surname, name, patronymic);
            System.out.printf("Birth year: %s%n", birthYear);
            System.out.printf("e-mail: %s%n%n", email);
        }
    }
}
