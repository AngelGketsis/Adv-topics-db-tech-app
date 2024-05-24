package org.tardis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tardis.data.CRDFDP;
import org.tardis.data.DataPoint;

import java.util.List;

@Repository
public interface CRDF_DataDAO extends JpaRepository<CRDFDP, char[]> {
    List<DataPoint> findAllByISO3OrderByYearAsc(char[] ISO3); // returns list of currencies
}
