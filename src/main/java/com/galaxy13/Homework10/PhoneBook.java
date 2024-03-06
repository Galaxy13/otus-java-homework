package com.galaxy13.Homework10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class User {
    private final String name;
    private String surname = "";
    private String secondName = "";

    public User(String name) {
        this.name = name;
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, String secondName) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (other.getName() != null && other.getSurname() != null && other.getSecondName() != null) {
            return (other.getName().equals(name) && other.getSecondName().equals(secondName) && other.getSurname().equals(surname));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (name + surname + secondName).hashCode();
    }
}

public class PhoneBook {
    private final Map<User, HashSet<String>> phoneBook = new HashMap<>();

    public void add(String number, String name) {
        if (number == null) {
            System.out.println("Null argument is not acceptable");
            return;
        }
        try {
            User user = buildUser(name);
            HashSet<String> userSet = phoneBook.computeIfAbsent(user, k -> new HashSet<>());
            userSet.add(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void find(String name) {
        try {
            User user = buildUser(name);
            HashSet<String> phoneNumbers = phoneBook.get(user);
            if (phoneNumbers != null) {
                System.out.println(phoneNumbers);
            } else {
                System.out.println("No such user exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean containsPhoneNumber(String number) {
        if (number == null) {
            System.out.println("Null argument is not acceptable");
            return false;
        }
        for (HashSet<String> phoneNumbers : phoneBook.values()) {
            if (phoneNumbers.contains(number)) {
                return true;
            }
        }
        return false;
    }

    private User buildUser(String name) throws Exception {
        if (name == null) {
            throw new Exception("Null argument is not acceptable");
        }
        String[] nameFormat = name.split(" ");
        if (nameFormat.length > 3) {
            throw new Exception("Too much arguments");
        }
        User user;
        switch (nameFormat.length) {
            case 1:
                user = new User(nameFormat[0]);
                break;
            case 2:
                user = new User(nameFormat[0], nameFormat[1]);
                break;
            case 3:
                user = new User(nameFormat[0], nameFormat[1], nameFormat[2]);
                break;
            default:
                return null;
        }
        return user;
    }
}
