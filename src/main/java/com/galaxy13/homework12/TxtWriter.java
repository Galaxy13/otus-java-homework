package com.galaxy13.homework12;

import java.io.*;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TxtWriter {
    public static File[] showTxt() {
        File currentDirectory = new File(".");
        FileFilter fileFilter = pathname -> pathname.toString().endsWith(".txt");
        File[] txtFiles = currentDirectory.listFiles(fileFilter);
        if (txtFiles == null) {
            return new File[]{};
        }
        return txtFiles;
    }

    public static String writeToFile(File txtFile, String stringToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true))) {
            writer.write(stringToWrite);
            return "Successful write to file " + txtFile.getName();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static String readTxtFile(File txtFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
            return "File contents:\n\n" + reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static String outDirectories(File[] txtFiles) {
        if (txtFiles.length == 0) {
            return "No txt files in current directory";
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Txt files in current directory:");
        for (int i = 0; i < txtFiles.length; i++) {
            stringJoiner.add((i + 1) + txtFiles[i].getName());
        }
        return stringJoiner.toString();
    }
}
