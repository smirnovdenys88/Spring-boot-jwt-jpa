package com.start.kit.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDTO {
    @Size(min = 4, message = "Please write at least 4 character a username")
    private String userName;

    @Email(regexp = "[^@]+@[^\\.]+\\..+", message = "Wrong format email")
    @NotNull(message = "Email should be valid")
    private String email;

    @Size(min = 6, message = "Please write at least 6 character a password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userName, userDTO.userName) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}