package com.galaxy13;

import com.galaxy13.homework12.TxtWriter;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static boolean checkInput(int input, int maxInput) {
        return 1 <= input && input <= maxInput;
    }

    private static void out(String txt) {
        System.out.println(txt);
    }

    private static int readFileNumber(File[] txtFiles, Scanner scanner) {
        System.out.printf("Select file: 1-%d%n", txtFiles.length);
        int fileIndex = 1;
        boolean intInputFlag = true;
        while (intInputFlag) {
            try {
                fileIndex = scanner.nextInt();
                if (!checkInput(fileIndex, txtFiles.length)) {
                    out("Number not in range. Try again");
                } else {
                    intInputFlag = false;
                }
            } catch (InputMismatchException e) {
                out("Not a number.Try again");
                scanner.nextLine();
            }
        }
        return fileIndex - 1;
    }

    private static String readTextFromConsole(Scanner scanner) {
        out("Enter text (double Enter to finish):");
        StringBuilder sb = new StringBuilder();
        scanner.nextLine();
        boolean scannerFlag = true;
        while (scannerFlag) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                scanner.close();
                scannerFlag = false;
            } else {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        File[] txtFiles = TxtWriter.showTxt();
        out(TxtWriter.outDirectories(txtFiles));
        Scanner scanner = new Scanner(System.in);

        int fileIndex = readFileNumber(txtFiles, scanner);

        String consoleText = readTextFromConsole(scanner);

        File selectedFile = txtFiles[fileIndex];

        out(TxtWriter.writeToFile(selectedFile, consoleText));
        out(TxtWriter.readTxtFile(selectedFile));
    }
}