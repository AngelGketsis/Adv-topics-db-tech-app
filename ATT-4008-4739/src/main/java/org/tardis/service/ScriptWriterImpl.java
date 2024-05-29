package org.tardis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.MessageFormat;

@Service
@AutoConfiguration
public class ScriptWriterImpl implements ScriptWriter {

    @Autowired
    static JdbcTemplate jdbcTemplate;

    static String dataDir = "classes/"; // from target dir
    static String dataDir2 = "./classes/"; // from target dir


    public void initializeDatabaseSQL() {
        ScriptWriterImpl.jdbcTemplate.execute("SOURCE " + ScriptWriterImpl.dataDir2 + "initializeDatabase.sql");
    }

    public void loadDataSQL() {
        ScriptWriterImpl.jdbcTemplate.execute("SOURCE " + ScriptWriterImpl.dataDir2 + "loaddata.sql");
    }

    public void writeInitializeDatabase() {
        String outputFile = dataDir2 + "initializedatabase.sql";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(generateInitData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLoadData() {
        String outputFile = dataDir2 + "loaddata.sql";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(generateLoadData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateLoadData() {
        String cwd = System.getProperty("user.dir");
        return MessageFormat.format("""
                LOAD DATA LOCAL INFILE '{0}data/geo_data.csv'
                  INTO TABLE Countries
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Display_Name, Area_SqKm, Population, Capital, Continent);
                  
                  LOAD DATA LOCAL INFILE '{0}data/currency_data.csv'
                  INTO TABLE Currencies
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, CurrencyCode, CurrencyName);
                  
                  LOAD DATA LOCAL INFILE '{0}data/region_data.csv'
                  INTO TABLE CountryRegionDetails
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Phone, Region_Code, Region_Name, Subregion_Code, Subregion_Name, Intermediate_Region_Code, Intermediate_Region_Name);
                  
                  LOAD DATA LOCAL INFILE '{0}data/development_data.csv'
                  INTO TABLE CountryDevelopmentStatus
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Status, Developed_or_Developing, @SIDS, @LLDC, @LDC)
                  SET
                      SIDS = IF(@SIDS = 'TRUE', TRUE, FALSE),
                      LLDC = IF(@LLDC = 'TRUE', TRUE, FALSE),
                      LDC = IF(@LDC = 'TRUE', TRUE, FALSE);
                  
                  
                  
                  -- Load data into ASTC_Data table
                  LOAD DATA LOCAL INFILE '{0}data/ASTC_data.csv'
                  INTO TABLE ASTC_Data
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Year, Value);
                  
                  -- Load data into ASTC_Data table
                  LOAD DATA LOCAL INFILE '{0}data/CRDF_data.csv'
                  INTO TABLE CRDF_Data
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Year, Value);
                  
                  -- Load data into ASTC_Data table
                  LOAD DATA LOCAL INFILE '{0}data/FC_data.csv'
                  INTO TABLE FC_Data
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Year, Value);
                  
                  -- Load data into ASTC_Data table
                  LOAD DATA LOCAL INFILE '{0}data/LCA_data.csv'
                  INTO TABLE LCA_Data
                  FIELDS TERMINATED BY ','
                  ENCLOSED BY '"'
                  LINES TERMINATED BY '\\n'
                  IGNORE 1 ROWS
                  (ISO3, Year, Value);
                """, cwd + dataDir);
    }

    private static String generateInitData() {
        return """
                -- Create the database
                CREATE DATABASE CountryDatabase;
                                
                -- Use the newly created database
                USE CountryDatabase;
                                
                -- Create Countries table
                CREATE TABLE Countries (
                    ISO3 CHAR(15) PRIMARY KEY,
                    Display_Name VARCHAR(255),
                    Area_SqKm DECIMAL(18, 2),
                    Population INT,
                    Capital VARCHAR(255),
                    Continent VARCHAR(255)
                );
                                
                -- Create Currencies table
                CREATE TABLE Currencies (
                    ISO3 CHAR(15),
                    CurrencyCode VARCHAR(3),
                    CurrencyName VARCHAR(255),
                    PRIMARY KEY(ISO3),
                    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
                );
                                
                -- Create CountryDetails table
                CREATE TABLE CountryRegionDetails (
                    ISO3 CHAR(15) PRIMARY KEY,
                    Phone VARCHAR(20),
                    Region_Code VARCHAR(10),
                    Region_Name VARCHAR(255),
                    Subregion_Code VARCHAR(10),
                    Subregion_Name VARCHAR(255),
                    Intermediate_Region_Code VARCHAR(10),
                    Intermediate_Region_Name VARCHAR(255),
                    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
                );
                                
                -- Create CountryDevelopmentStatus table
                CREATE TABLE CountryDevelopmentStatus (
                    ISO3 CHAR(15) PRIMARY KEY,
                    Status VARCHAR(50),
                    Developed_or_Developing VARCHAR(20),
                    SIDS BOOLEAN not null default FALSE,
                    LLDC BOOLEAN not null default FALSE,
                    LDC BOOLEAN not null default FALSE,
                    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
                );
                                
                -- Create CountryData table
                CREATE TABLE ASTC_Data (
                    ISO3 CHAR(15),
                    Year INT,
                    Value DOUBLE
                );
                                
                -- Create CountryData table
                CREATE TABLE CRDF_Data (
                    ISO3 CHAR(15),
                    Year INT,
                    Value INT
                );
                                
                -- Create CountryData table
                CREATE TABLE FC_Data (
                    ISO3 CHAR(15),
                    Year INT,
                    Value DOUBLE
                );
                                
                -- Create CountryData table
                CREATE TABLE LCA_Data (
                    ISO3 CHAR(15),
                    Year INT,
                    Value DOUBLE
                );
                """;
    }

}
