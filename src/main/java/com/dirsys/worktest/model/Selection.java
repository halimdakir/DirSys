package com.dirsys.worktest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public
class Selection{
    private String filter;
    private String [] values;


    @Override
    public String toString() {
        return "{" +
                "filter:'" + filter + '\'' +
                ", values:" + Arrays.toString(values)+
                '}';
    }
}
