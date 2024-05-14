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
    LLDC BOOLEAN,
    LDC BOOLEAN,
    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
);

-- Create CountryData table
CREATE TABLE ASTC_Data (
    ISO3 CHAR(15),
    Year INT,
    Value DOUBLE,
    PRIMARY KEY (ISO3, Year),
    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
);

-- Create CountryData table
CREATE TABLE CRDF_Data (
    ISO3 CHAR(15),
    Year INT,
    Value INT,
    PRIMARY KEY (ISO3, Year),
    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
);

-- Create CountryData table
CREATE TABLE FC_Data (
    ISO3 CHAR(15),
    Year INT,
    Value DOUBLE,
    PRIMARY KEY (ISO3, Year),
    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
);

-- Create CountryData table
CREATE TABLE LCA_Data (
    ISO3 CHAR(15),
    Year INT,
    Value DOUBLE,
    PRIMARY KEY (ISO3, Year),
    FOREIGN KEY(ISO3) REFERENCES Countries(ISO3)
);
