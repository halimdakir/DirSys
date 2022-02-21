package com.dirsys.worktest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Query{
    private String code;
    private Selection selection;


    @Override
    public String toString() {
        return "{" +
                "code:'" + code + '\'' +
                ", selection:" + selection +
                '}';
    }
}



