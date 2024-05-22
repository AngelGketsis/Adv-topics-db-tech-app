package org.tardis.data;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LCA_Data")
public class LCADP extends DataPoint {

    public LCADP(char[] ISO3, int year, double value){
        super(ISO3, year, value);
    }

}
