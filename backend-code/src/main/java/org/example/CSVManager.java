package org.example;

import java.io.*;

public class CSVManager {

    public static void copyColumns(String inputFile, String outputFile, int[] columnsToCopy) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(","); // Assuming CSV delimiter is comma (,)

                StringBuilder newLine = new StringBuilder();
                for (int columnIndex : columnsToCopy) {
                    if (columnIndex >= 0 && columnIndex < columns.length) {
                        newLine.append(columns[columnIndex]).append(",");
                    }
                }
                // Remove the last comma
                if (!newLine.isEmpty()) {
                    newLine.deleteCharAt(newLine.length() - 1);
                }

                writer.write(newLine.toString());
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) {
        String inputFile = "../countries_archive/countries.csv"; // Path to input CSV file
        String outputFile = "geo_data.csv"; // Path to output CSV file
        int[] columnsToCopy = {1, 4, 22, 23}; // Indices of columns to copy (0-based)

        try {
            copyColumns(inputFile, outputFile, columnsToCopy);
            System.out.println("Columns copied successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
