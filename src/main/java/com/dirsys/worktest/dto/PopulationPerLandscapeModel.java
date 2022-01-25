package com.dirsys.worktest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopulationPerLandscapeModel {
    private String landscape_code;
    private String landscape_name;
    private String [] landscape_population;

}
