package org.tardis.service;

import org.tardis.data.DataPoint;

import java.util.ArrayList;
import java.util.List;

public interface ChartService {
    ArrayList<ArrayList<DataPoint>> getASTCDataPoints(ArrayList<char[]> isos);
    ArrayList<ArrayList<DataPoint>> getCRDFDataPoints(ArrayList<char[]> isos);
    ArrayList<ArrayList<DataPoint>> getFCDataPoints(ArrayList<char[]> isos);
    ArrayList<ArrayList<DataPoint>> getLCADataPoints(ArrayList<char[]> isos);
}
