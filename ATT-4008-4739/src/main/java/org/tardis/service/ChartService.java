package org.tardis.service;

import org.tardis.dao.ASTC_DataDAO;
import org.tardis.data.DataPoint;

import java.util.List;

public interface ChartService {
    List<List<DataPoint>> getASTCDataPoints(List<char[]> isos);
    List<List<DataPoint>> getCRDFDataPoints(List<char[]> isos);
    List<List<DataPoint>> getFCDataPoints(List<char[]> isos);
    List<List<DataPoint>> getLCADataPoints(List<char[]> isos);
}
