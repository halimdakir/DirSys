package com.dirsys.worktest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PercentageChangeModel {
    private String year;
    private String population;
    private String percentage_change;

    public PercentageChangeModel(String year, String population, String percentage_change) {
        this.year = year;
        this.population = population;
        this.percentage_change = percentage_change;
    }
}
