package com.dirsys.worktest.dto;

import lombok.Data;

@Data
public class PopulationSwedenSingelYearModel {
    private String year;
    private String population;


    public PopulationSwedenSingelYearModel(String year, String population) {
        this.year = year;
        this.population = population;
    }
}
