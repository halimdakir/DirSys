package com.dirsys.worktest;

import com.dirsys.worktest.model.PopulationSwedenSeveralYearsModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEntityModel {

    private Column[] columns;
    private Comment[] comments;
    private List<PopulationSwedenSeveralYearsModel> data;
    private Metadata[] metadata;

}

class Metadata {
    private String infofile;
    private String updated;
    private String label;
    private String source;
}

class Comment {
//    private String variable;
//    private String value;
//    private String comment;
}

class Column {
    private  String code;
    private String text;
    private String type;
}

