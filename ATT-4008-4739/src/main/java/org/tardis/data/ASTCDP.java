package org.tardis.data;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ASTC_Data")
public class ASTCDP extends DataPoint {

    public ASTCDP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
