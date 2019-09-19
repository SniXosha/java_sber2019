package ru.myhw.task1.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigLoader {

    public static Config loadFromFile(String filename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        String type = null;
        Double value = null;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.trim();
            String[] parts = line.split("=");
            if (parts.length != 2) {
                continue;
            }
            if (parts[0].equals("type") && parts[1].length() > 0) {
                type = parts[1];
                continue;
            }
            if (parts[0].equals("price") && parts[1].length() > 0) {
                value = Double.parseDouble(parts[1]);
            }
        }
        if (type != null && value != null) {
            return new Config(type, value);
        } else {
            return null;
        }
    }
}
