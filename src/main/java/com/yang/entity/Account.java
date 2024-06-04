package com.yang.entity;

import lombok.Data;

@Data
public class Account {

    public Integer id;
    public String username;
    public String password;
    public String perms;
    public String role;

}
