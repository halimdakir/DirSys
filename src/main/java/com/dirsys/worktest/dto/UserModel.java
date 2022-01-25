package com.dirsys.worktest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String fullName;
    private String email;
    private String password;
}
