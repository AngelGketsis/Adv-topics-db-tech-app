package org.tardis.data;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CRDF_Data")
public class CRDFDP extends DataPoint {

    public CRDFDP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
