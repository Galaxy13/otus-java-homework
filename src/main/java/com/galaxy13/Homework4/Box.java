package com.galaxy13.Homework4;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Box {
    private final BoxSize boxSize;
    @NonNull
    private BoxColor color;
    private BoxState boxState = BoxState.CLOSED;
    private String object = "";

    public void openBox() {
        boxState = BoxState.OPENED;
        System.out.println("Box opened");
    }

    public void closeBox() {
        boxState = BoxState.CLOSED;
        System.out.println("Box closed");
    }

    public void changeColor(BoxColor newBoxColor) {
        color = newBoxColor;
        System.out.printf("Color of box changed to: %s%n", color.getColor());
    }

    public void putObject(String newObject) {
        if (object.isEmpty() && boxState.equals(BoxState.OPENED)) {
            object = newObject;
            System.out.printf("New object '%s' was put in a box%n", newObject);
        } else if (boxState.equals(BoxState.CLOSED)) {
            System.out.println("Box is closed. Open the box first");
        } else {
            System.out.println("Box is not empty. Remove current object first");
        }
    }

    public void takeObject() {
        if (!object.isEmpty() && boxState.equals(BoxState.OPENED)) {
            System.out.printf("Object '%s' has taken from box%n", object);
            object = "";
        } else if (boxState.equals(BoxState.CLOSED)) {
            System.out.println("Box is closed. Open the box first");
        } else {
            System.out.println("Nothing to remove");
        }
    }

    public void retrieveInfo() {
        System.out.printf("%nHeight: %s%nWidth: %s%nLength: %s%n", boxSize.getHeight(), boxSize.getWidth(), boxSize.getLength());
        System.out.printf("Color: %s%n", color.getColor());
        System.out.printf("State: %s%n", boxState.getValue());
        if (object.isEmpty()) {
            System.out.println("No object stashed in box");
        } else {
            System.out.printf("Object '%s' stashed in box%n", object);
        }
        System.out.print("\n");
    }
}
