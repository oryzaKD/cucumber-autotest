package com.example.stepdefinitions;

import java.io.*;

public class FileCounter {
    private static final String FILE_PATH = "counter.txt";

    // Method to read the counter value from file
    public static int readCounter() {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                counter = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading counter file: " + e.getMessage());
        }
        return counter;
    }

    // Method to write the counter value to file
    public static void writeCounter(int counter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(counter));
        } catch (IOException e) {
            System.out.println("Error writing to counter file: " + e.getMessage());
        }
    }

    // Method to increment and save the counter
    public static int incrementAndSaveCounter() {
        int counter = readCounter();
        counter++;
        writeCounter(counter);
        return counter;
    }
}
