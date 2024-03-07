package com.galaxy13;

import com.galaxy13.homework12.TxtWriter;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File[] txtFiles = TxtWriter.showTxt();
        if (txtFiles.length == 0) {
            System.out.println("No txt files in current directory");
            return;
        }
        System.out.println("Txt files in current directory:");
        for (int i = 0; i < txtFiles.length; i++) {
            System.out.printf("%d. %s%n", i + 1, txtFiles[i].getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.printf("Select file: 1-%d%n", txtFiles.length);
        int selectedFile;
        while (true) {
            try {
                selectedFile = scanner.nextInt();
                if (selectedFile < 1 || selectedFile > txtFiles.length) {
                    System.out.println("Number not in range. Try again");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Not a number.Try again");
                scanner.nextLine();
            }
        }

        System.out.println("Enter text (double Enter to finish):");
        StringBuilder sb = new StringBuilder();
        scanner.nextLine();
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                scanner.close();
                break;
            } else {
                sb.append(line).append("\n");
            }
        }

        try {
            TxtWriter.writeToFile(txtFiles[selectedFile - 1], sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("File contents:\n");
            System.out.println(TxtWriter.readTxtFile(txtFiles[selectedFile - 1]));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}