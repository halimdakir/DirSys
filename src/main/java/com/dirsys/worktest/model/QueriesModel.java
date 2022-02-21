package com.dirsys.worktest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Arrays;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueriesModel {
    private Query [] query;
    private QueryResponse response;


    @Override
    public String toString() {
        return "{" +
                "query:" + Arrays.toString(query) +
                ", response:" + response +
                '}';
    }
}



