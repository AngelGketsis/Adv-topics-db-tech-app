package org.tardis.backend;

import java.io.*;

public class CSVManager {

    public static void copyColumns(String inputFile, String outputFile, int[] columnsToCopy) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

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


            String out = newLine.toString();
            writer.write(out);
            writer.newLine();
            //writer.write("sssssssssssssssdssdasdasdatdfyuuuuuuuutdyfuyyffyjvhjffjfyvfyvujfyvfyfuyyufyfyufuyfuyssss");
            //writer.newLine();
        }
    }

    public static void main(String[] args) {
        String inputFile = "../src/main/resources/data/countries.csv"; // Path to input CSV file


        String[] outputFile = {"../src/main/resources/data/geo_data.csv", "../src/main/resources/data/currency_data.csv", "../src/main/resources/data/development_data.csv", "../src/main/resources/data/region_data.csv"}; // Paths to output CSV files
        int[][] columnsToCopy = {{1, 4, 22, 23, 6, 7}, {1, 8, 9}, {1, 17, 18, 19, 20, 21}, {1, 10, 11, 12, 13, 14}}; // Indices of columns to copy (0-based)
        for(int i = 0; i < outputFile.length; i++)
        {
            try {
                copyColumns(inputFile, outputFile[i], columnsToCopy[i]);
                System.out.println("Columns copied successfully!");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

    }

}