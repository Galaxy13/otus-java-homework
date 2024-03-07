package com.galaxy13.homework12;

import java.io.*;
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

    public static void writeToFile(File txtFile, String stringToWrite) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true));
        writer.write(stringToWrite);
        writer.close();
    }

    public static String readTxtFile(File txtFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(txtFile));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
