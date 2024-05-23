package org.tardis.data;


import jakarta.persistence.*;

@Entity
@Table(name = "FC_Data")
public class FCDP extends DataPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ISO3", columnDefinition = "char")
    protected char[] ISO3;


    public FCDP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
