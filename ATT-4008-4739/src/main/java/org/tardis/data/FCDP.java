package org.tardis.data;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FC_Data")
public class FCDP extends DataPoint {

    public FCDP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
