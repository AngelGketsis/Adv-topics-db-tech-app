package org.tardis.data;


import jakarta.persistence.*;

@Entity
@Table(name = "CRDF_Data")
public class CRDFDP extends DataPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISO3", columnDefinition = "char")
    protected char[] ISO3;
    @Column(name = "Year", columnDefinition = "int")
    private int year;
    @Column(name = "Value", columnDefinition = "int")
    private double value;

    public CRDFDP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
