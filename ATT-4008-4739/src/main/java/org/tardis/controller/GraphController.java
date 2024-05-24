package org.tardis.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tardis.service.ChartService;
import org.tardis.data.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AutoConfiguration
public class GraphController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/graphs")
    public String listFiles(Model model) {
        return "graphs/index.html";
    }

    @GetMapping("/graphs/annual_surface_temperature_changes")
    public String getChart(@NotNull Model model) {
        List<String> countries = List.of("GRC", "USA", "CAN"); // Add other country codes as needed
        model.addAttribute("countries", countries);
        return "graphs/annual_surface_temperature_changes";
    }

    @PostMapping("/graphs/annual_surface_temperature_changes/data")
    @ResponseBody
    public List<CountryData> getCountryData(@RequestBody List<String> countries) {
        System.out.println("Received countries: " + countries);  // Log the received countries
        ArrayList<char[]> isoCodes = new ArrayList<>(countries.stream().map(String::toCharArray).collect(Collectors.toList()));
        ArrayList<ArrayList<DataPoint>> dataPoints = chartService.getASTCDataPoints(isoCodes);

        List<CountryData> result = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            String country = countries.get(i);
            ArrayList<DataPoint> points = dataPoints.get(i);
            result.add(new CountryData(country, points));
        }

        return result;
    }

    public static class CountryData {
        public String country;
        public List<DataPoint> data;

        public CountryData(String country, List<DataPoint> data) {
            this.country = country;
            this.data = data;
        }
    }

}
