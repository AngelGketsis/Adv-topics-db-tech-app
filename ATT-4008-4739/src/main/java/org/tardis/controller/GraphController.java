package org.tardis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tardis.service.ChartService;
import org.tardis.data.DataPoint;

import java.util.List;

@Controller
public class GraphController {
    private static final String BASE_PATH = "./classes/data";
    private static final String[] files = {
            "Forest_and_Carbon.csv",
            "Land_Cover_Accounts.csv",
            "countries.csv",
            "Climate-related_Disasters_Frequency.csv",
            "Annual_Surface_Temperature_Change.csv"
    };

    @Autowired
    private ChartService chartService;

    @GetMapping("/graph")
    public String listFiles(Model model) {

        return "graphs";
    }

    @GetMapping("/graphs/annual_surface_temperature_changes")
    public String getChart(Model model) {
        List<char[]> countries = null;
        List<List<DataPoint>> dataPoints = chartService.getASTCDataPoints(countries);
        model.addAttribute("dataPoints", dataPoints);
        return "graphs/annual_surface_temperature_changes";
    }
}