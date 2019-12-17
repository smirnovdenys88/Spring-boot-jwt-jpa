package com.full.circle.registration.restjwtpostgres.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String email;

    private String tokenConfirmEmail;
    private boolean confirmEmail;

    @JsonIgnore
    private String password;

    public User() {
        //serialization
    }

    public User(String userName, String email, String token, String password) {
        this.userName = userName;
        this.email = email;
        this.tokenConfirmEmail = token;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getTokenConfirmEmail() {
        return tokenConfirmEmail;
    }

    public void setTokenConfirmEmail(String tokenConfirmEmail) {
        this.tokenConfirmEmail = tokenConfirmEmail;
    }

    public boolean isConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(boolean confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {

        private long id;
        private String userName;
        private String email;
        private String token;
        private String pass;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setToken(String token){
            this.token = token;
            return this;
        }

        public Builder setPass(String pass) {
            this.pass = pass;
            return this;
        }

        public User build() {
            return new User(userName, email, token, pass);
        }
    }
}