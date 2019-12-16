package com.full.circle.registration.restjwtpostgres.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO {
    @NotEmpty(message = "Please provide a username")
    private String username;

    @Email(regexp = "[^@]+@[^\\.]+\\..+", message = "Wrong format email")
    @NotNull(message = "Email should be valid")
    private String email;

    @Min(value = 4, message = "Please write at least 4 character a username")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}