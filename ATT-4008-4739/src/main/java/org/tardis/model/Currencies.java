package org.tardis.model;

import jakarta.persistence.*;

@Entity
@Table(name="Currencies")
public class Currencies {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ISO3")
    private char[] ISO3;

    @Column(name="Year")
    private int year;

    @Column(name="Value")
    private double value;


    public Currencies(char[] iso3, int year, double value) {
        ISO3 = iso3;
        this.year = year;
        this.value = value;
    }
}
