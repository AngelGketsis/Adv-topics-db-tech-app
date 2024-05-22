package org.tardis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tardis.dao.ASTC_DataDAO;
import org.tardis.data.DataPoint;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ASTC_DataDAO ASTCRepository, CRDFRepository, FCRepository, LCARepository;

    @Autowired
    public List<List<DataPoint>> getASTCDataPoints(List<char[]> ISOs) {
        List<List<DataPoint>> dataPoints = new ArrayList<List<DataPoint>>();
        for (int i = 0; i < ISOs.size(); i++) {
            dataPoints.add(ASTCRepository.findAllByISO3OrderByYearAsc(ISOs.get(i)));
        }
        return dataPoints;
    }

    @Autowired
    public List<List<DataPoint>> getCRDFDataPoints(List<char[]> isos) {
        List<List<DataPoint>> dataPoints = new ArrayList<List<DataPoint>>();
        for (int i = 0; i < isos.size(); i++) {
            dataPoints.add(CRDFRepository.findAllByISO3OrderByYearAsc(isos.get(i)));
        }
        return dataPoints;
    }

    @Autowired
    public List<List<DataPoint>> getFCDataPoints(List<char[]> isos) {
        List<List<DataPoint>> dataPoints = new ArrayList<List<DataPoint>>();
        for (int i = 0; i < isos.size(); i++) {
            dataPoints.add(FCRepository.findAllByISO3OrderByYearAsc(isos.get(i)));
        }
        return dataPoints;
    }

    @Autowired
    public List<List<DataPoint>> getLCADataPoints(List<char[]> isos) {
        List<List<DataPoint>> dataPoints = new ArrayList<List<DataPoint>>();
        for (int i = 0; i < isos.size(); i++) {
            dataPoints.add(LCARepository.findAllByISO3OrderByYearAsc(isos.get(i)));
        }
        return dataPoints;
    }

}
