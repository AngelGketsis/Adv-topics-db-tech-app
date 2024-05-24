package org.tardis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;
import org.tardis.bean.DataBean;
import org.tardis.dao.*;
import org.tardis.data.DataPoint;

import java.util.ArrayList;
import java.util.List;

@Service
@AutoConfiguration
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ASTC_DataDAO ASTCRepository;
    @Autowired
    private CRDF_DataDAO CRDFRepository;
    @Autowired
    private FC_DataDAO FCRepository;
    @Autowired
    private LCA_DataDAO LCARepository;

    public ArrayList<ArrayList<DataPoint>> getASTCDataPoints(ArrayList<char[]> ISOs) {
        DataBean dataBean = new DataBean();

        ArrayList<ArrayList<DataPoint>> dataPoints = new ArrayList<ArrayList<DataPoint>>();
        for (int i = 0; i < ISOs.size(); i++) {
            dataPoints.add(ASTCRepository.findAllByISO3OrderByYearAsc(ISOs.get(i)));
        }
        return dataPoints;
    }

    public ArrayList<ArrayList<DataPoint>> getCRDFDataPoints(ArrayList<char[]> isos) {
        ArrayList<ArrayList<DataPoint>> dataPoints = new ArrayList<ArrayList<DataPoint>>();
        for (int i = 0; i < isos.size(); i++) {
            dataPoints.add(CRDFRepository.findAllByISO3OrderByYearAsc(isos.get(i)));
        }
        return dataPoints;
    }

    public ArrayList<ArrayList<DataPoint>> getFCDataPoints(ArrayList<char[]> isos) {
        ArrayList<ArrayList<DataPoint>> dataPoints = new ArrayList<ArrayList<DataPoint>>();
        for (int i = 0; i < isos.size(); i++) {
            dataPoints.add(FCRepository.findAllByISO3OrderByYearAsc(isos.get(i)));
        }
        return dataPoints;
    }

    public ArrayList<ArrayList<DataPoint>> getLCADataPoints(ArrayList<char[]> isos) {
        ArrayList<ArrayList<DataPoint>> dataPoints = new ArrayList<ArrayList<DataPoint>>();
        for (int i = 0; i < isos.size(); i++) {
            dataPoints.add(LCARepository.findAllByISO3OrderByYearAsc(isos.get(i)));
        }
        return dataPoints;
    }

}
