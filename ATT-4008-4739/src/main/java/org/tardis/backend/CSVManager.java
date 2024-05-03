package org.tardis.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVManager {



    public static void copyColumns(String inputFile, String outputFile, String[] columnsToCopy) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("Input file is empty.");
            }
            String[] headers = parseCSVLine(headerLine); // Parsing header line

            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i], i);
            }

            StringBuilder newHeaderLine = new StringBuilder();
            for (String column : columnsToCopy) {
                if (headerMap.containsKey(column)) {
                    newHeaderLine.append(column).append(",");
                } else {
                    throw new IllegalArgumentException("Column '" + column + "' not found in the input CSV file.");
                }
            }
            // Remove the last comma
            if (newHeaderLine.length() > 0) {
                newHeaderLine.deleteCharAt(newHeaderLine.length() - 1);
            }

            writer.write(newHeaderLine.toString());
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = parseCSVLine(line);

                StringBuilder newLine = new StringBuilder();
                for (String column : columnsToCopy) {
                    int index = headerMap.get(column);
                    newLine.append(columns[index]).append(",");
                }
                // Remove the last comma
                if (newLine.length() > 0) {
                    newLine.deleteCharAt(newLine.length() - 1);
                }

                writer.write(newLine.toString());
                writer.newLine();
            }
        }
    }

    // Custom CSV line parser to handle quoted values containing commas
    private static String[] parseCSVLine(String line) {
        List<String> columns = new ArrayList<>();
        StringBuilder currentColumn = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                columns.add(currentColumn.toString());
                currentColumn.setLength(0);
            } else {
                currentColumn.append(c);
            }
        }

        columns.add(currentColumn.toString());
        return columns.toArray(new String[0]);
    }



    public static void processCSV(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            writer.write("ISO3,Year,Value\n");

            String line;
            boolean firstLine = true; // Flag to skip the first line
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip the first line (header)
                }

                String[] columns = line.split(",");

                // Extract ISO3
                String iso3 = columns[0].trim(); // Assuming ISO3 is at index 0

                // Process F columns
                for (int i = 1; i < columns.length; i++) {
                    String year = Integer.toString(1960 + i); // Extracting year from column index
                    String value = columns[i].trim();

                    if (!value.isEmpty()) {
                        writer.write(iso3 + "," + year + "," + value + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String inputFile = "../src/main/resources/data/countries.csv"; // Path to input CSV file

        String[] outputFile = {"../src/main/resources/data/geo_data.csv", "../src/main/resources/data/currency_data.csv", "../src/main/resources/data/development_data.csv", "../src/main/resources/data/region_data.csv"}; // Paths to output CSV files
        String[][] columnsToCopy = {{"ISO3", "Display_Name", "Area_SqKm", "Population", "Capital", "Continent"}, {"ISO3", "CurrencyCode", "CurrencyName"}, {"ISO3", "Status","Developed or Developing","Small Island Developing States (SIDS)","Land Locked Developing Countries (LLDC)","Least Developed Countries (LDC)"}, {"ISO3", "Phone","Region Code","Region Name","Sub-region Code","Sub-region Name","Intermediate Region Code","Intermediate Region Name"}}; // Indices of columns to copy (0-based)
        for(int i = 0; i < outputFile.length; i++)
        {
            try {
                copyColumns(inputFile, outputFile[i], columnsToCopy[i]);
                System.out.println("Columns copied successfully!");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        try {
            copyColumns("../src/main/resources/data/Annual_Surface_Temperature_Change.csv", "../src/main/resources/data/ASTC_idoc.csv", new String[] {"ISO3","F1961","F1962","F1963","F1964","F1965","F1966","F1967","F1968","F1969","F1970","F1971","F1972","F1973","F1974","F1975","F1976","F1977","F1978","F1979","F1980","F1981","F1982","F1983","F1984","F1985","F1986","F1987","F1988","F1989","F1990","F1991","F1992","F1993","F1994","F1995","F1996","F1997","F1998","F1999","F2000","F2001","F2002","F2003","F2004","F2005","F2006","F2007","F2008","F2009","F2010","F2011","F2012","F2013","F2014","F2015","F2016","F2017","F2018","F2019","F2020","F2021","F2022"});
            System.out.println("Columns copied successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        processCSV("../src/main/resources/data/ASTC_idoc.csv", "../src/main/resources/data/ASTC_data.csv");

        try {
            copyColumns("../src/main/resources/data/Climate-related_Disasters_Frequency.csv", "../src/main/resources/data/CRDF_idoc.csv", new String[] {"ISO3","F1980","F1981","F1982","F1983","F1984","F1985","F1986","F1987","F1988","F1989","F1990","F1991","F1992","F1993","F1994","F1995","F1996","F1997","F1998","F1999","F2000","F2001","F2002","F2003","F2004","F2005","F2006","F2007","F2008","F2009","F2010","F2011","F2012","F2013","F2014","F2015","F2016","F2017","F2018","F2019","F2020","F2021","F2022"});
            System.out.println("Columns copied successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        processCSV("../src/main/resources/data/CRDF_idoc.csv", "../src/main/resources/data/CRDF_data.csv");

        try {
            copyColumns("../src/main/resources/data/Land_Cover_Accounts.csv", "../src/main/resources/data/LCA_idoc.csv", new String[] {"ISO3","F1992","F1993","F1994","F1995","F1996","F1997","F1998","F1999","F2000","F2001","F2002","F2003","F2004","F2005","F2006","F2007","F2008","F2009","F2010","F2011","F2012","F2013","F2014","F2015","F2016","F2017","F2018","F2019","F2020"});
            System.out.println("Columns copied successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        processCSV("../src/main/resources/data/LCA_idoc.csv", "../src/main/resources/data/LCA_data.csv");

        try {
            copyColumns("../src/main/resources/data/Forest_and_Carbon.csv", "../src/main/resources/data/FC_idoc.csv", new String[] {"ISO3","F1992","F1993","F1994","F1995","F1996","F1997","F1998","F1999","F2000","F2001","F2002","F2003","F2004","F2005","F2006","F2007","F2008","F2009","F2010","F2011","F2012","F2013","F2014","F2015","F2016","F2017","F2018","F2019","F2020"});
            System.out.println("Columns copied successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        processCSV("../src/main/resources/data/FC_idoc.csv", "../src/main/resources/data/FC_data.csv");

    }

}