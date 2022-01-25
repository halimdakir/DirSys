package com.dirsys.worktest.entity;
import com.dirsys.worktest.dto.Roles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

//    @Enumerated(EnumType.STRING)
//    private Roles role;

    private String roles = "";


    private int active;

    private String permissions = "";


    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public User(String fullName, String email, String password, String roles, String permissions) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.active = 1;
        this.permissions = permissions;
    }

    public User() {
    }


    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
