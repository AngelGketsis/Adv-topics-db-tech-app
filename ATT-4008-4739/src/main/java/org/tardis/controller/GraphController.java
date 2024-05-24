package org.tardis.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tardis.service.ChartService;
import org.tardis.data.DataPoint;

import java.util.List;

@Controller
@AutoConfiguration
public class GraphController {

    @Autowired
    private ChartService chartService;
/*
    @GetMapping("/graphs")
    public String listFiles(Model model) {

        return "graphs/index.html";
    }
*/
    @GetMapping("/graphs/annual_surface_temperature_changes")
    public String getChart(@NotNull Model model) {
        List<char[]> countries = null;
        List<List<DataPoint>> dataPoints = chartService.getASTCDataPoints(countries);
        model.addAttribute("dataPoints", dataPoints);
        return "graphs/annual_surface_temperature_changes";
    }
}