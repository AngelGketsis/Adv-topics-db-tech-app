package org.tardis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

    @Autowired
    public List<List<DataPoint>> getASTCDataPoints(List<char[]> ISOs) {
        DataBean dataBean = new DataBean();

        List<List<DataPoint>> dataPoints = dataBean.list();
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
