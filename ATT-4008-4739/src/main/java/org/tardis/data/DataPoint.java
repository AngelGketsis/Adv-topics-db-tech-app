package org.tardis.data;

import javax.persistence.*;

public class DataPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ISO3")
    protected char[] ISO3;
    @Column(name = "Year")
    protected int year;
    @Column(name = "Value")
    protected double value;

    public DataPoint(char[] ISO3, int year, double value) {
        this.ISO3 = ISO3;
        this.year = year;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + this.year + ", " + this.value + "]";
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
