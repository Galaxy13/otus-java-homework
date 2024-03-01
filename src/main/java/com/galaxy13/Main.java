package com.galaxy13;

import com.galaxy13.Homework4.Box;
import com.galaxy13.Homework4.BoxColor;
import com.galaxy13.Homework4.BoxSize;
import com.galaxy13.Homework4.User;

import java.time.Year;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // User
        Random random = new Random();
        String[] names = {"Alexey", "Ivan", "Alexander", "Grigoriy", "Gennadiy", "Denis", "Stanislav", "Maksim", "Eugene", "Yuri"};
        String[] surnames = {"Lebedev", "Ivanov", "Frolov", "Petrov", "Chizhikov", "Volkov", "Sokolov", "Popov", "Lermontov", "Morozov"};
        String[] patronymics = {"Alexandrovich", "Olegovich", "Sergeevich", "Yurevich", "Vitalevich", "Viktorovich", "Denisovich", "Danilovich", "Valentinovich", "Artemovich"};
        int[] births = random.ints(10, 1950, 2020)
                .toArray();
        String[] emails = {"123@ya.ru", "go@mail.ru", "no@inbox.com", "java@gmail.com", "addr@rambler.ru", "rty@mai.ru", "490@mail.ru", "redi@this.uk, cia@inbox.com", "htr@tui.uk", "work@mail.ru"};
        int year = Year.now().getValue();
        for (int i = 0; i < names.length; i++) {
            new User(names[i], surnames[i], patronymics[i], births[i], emails[i]).retrieveInfo(year);
        }

        // Box
        BoxSize boxSize = new BoxSize(20, 30, 40);
        Box box = new Box(boxSize, BoxColor.BLUE);
        box.retrieveInfo();

        box.openBox();
        box.retrieveInfo();

        box.putObject("GC");
        box.retrieveInfo();

        box.takeObject();
        box.retrieveInfo();

        box.closeBox();
        box.retrieveInfo();

        box.changeColor(BoxColor.RED);
        box.retrieveInfo();

        box.takeObject();
        box.openBox();
        box.takeObject();

        box.putObject("UZI 9mm");
        box.putObject("Green Herb");
        box.retrieveInfo();
    }
}