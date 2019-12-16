package com.full.circle.registration.restjwtpostgres.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class UserDTO {
    @Min(value = 8, message = "Please write at least 8 characters a username")
    @NotEmpty(message = "Please provide a username")
    private String username;

    @Min(value = 8, message = "Please write at least 8 character a username")
    @NotEmpty(message = "Please provide a password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}