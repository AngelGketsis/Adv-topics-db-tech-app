package org.tardis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tardis.data.DataPoint;
import org.tardis.data.FCDP;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FC_DataDAO extends JpaRepository<FCDP, char[]> {
    ArrayList<DataPoint> findAllByISO3OrderByYearAsc(char[] ISO3); // returns list of currencies
}
