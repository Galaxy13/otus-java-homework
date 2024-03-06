package com.galaxy13.Homework9;

public class User implements Comparable<User> {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User otherUser) {
        return Integer.compare(age, otherUser.getAge());
    }

    @Override
    public String toString() {
        return String.format("User %s with age: %d", name, age);
    }
}
