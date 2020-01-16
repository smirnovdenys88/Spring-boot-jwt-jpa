package com.start.kit.model;

import java.util.Objects;

public class AuthToken {
    private String token;
    private String username;

    public AuthToken() {
        //serialization
    }

    public AuthToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(token, authToken.token) &&
                Objects.equals(username, authToken.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, username);
    }
}
