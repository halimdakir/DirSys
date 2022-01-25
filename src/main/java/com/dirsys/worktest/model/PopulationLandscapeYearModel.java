package com.dirsys.worktest.model;

import lombok.Data;

import java.util.List;

@Data
public class PopulationLandscapeYearModel {
    private String year;
    private List<SortPopulationModel> data;

    public PopulationLandscapeYearModel(String year, List<SortPopulationModel> data) {
        this.year = year;
        this.data = data;
    }
}

