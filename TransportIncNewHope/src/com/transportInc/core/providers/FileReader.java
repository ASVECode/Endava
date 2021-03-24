package com.transportInc.core.providers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private Scanner sc;

    public FileReader(File file) {
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }

    public List<String> readFile(String fileName) {
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(readLine());
        }
        return input;
    }

    public String readLine() {
        return sc.nextLine();
    }
}
