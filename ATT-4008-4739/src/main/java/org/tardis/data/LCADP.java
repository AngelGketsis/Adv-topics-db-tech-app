package org.tardis.data;


import jakarta.persistence.*;

@Entity
@Table(name = "LCA_Data")
public class LCADP extends DataPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISO3", columnDefinition = "char")
    protected char[] ISO3;
    @Column(name = "Year", columnDefinition = "int")
    private int year;
    @Column(name = "Value", columnDefinition = "int")
    private double value;


    public LCADP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
