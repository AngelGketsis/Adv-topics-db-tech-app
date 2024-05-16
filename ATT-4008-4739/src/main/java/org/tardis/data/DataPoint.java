package org.tardis.data;

public class DataPoint {

    private char[] ISO3;
    private int year;
    private double value;

    public DataPoint(char[] ISO3, int year, double value){
        this.ISO3 = ISO3;
        this.year = year;
        this.value = value;
    }

    public char[] getISO3() {
        return ISO3;
    }

    public int getYear() {
        return year;
    }

    public double getValue() {
        return value;
    }

}
