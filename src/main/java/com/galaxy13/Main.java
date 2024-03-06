package com.galaxy13;

import com.galaxy13.Homework10.PhoneBook;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("+7923478", "Alexandr Ivanov");
        phoneBook.find("Alexandr Ivanov");
        phoneBook.find("Someone");

        phoneBook.add("+9999", "Alexey Frolov");
        phoneBook.add("71823740", "Alexey Frolov");
        phoneBook.find("Alexey Frolov");

        System.out.println(phoneBook.containsPhoneNumber("+99999"));
        System.out.println(phoneBook.containsPhoneNumber("0000"));

        phoneBook.add(null, null);
        phoneBook.find(null);

        phoneBook.find("aff dgr tafj adioi ee");
    }
}