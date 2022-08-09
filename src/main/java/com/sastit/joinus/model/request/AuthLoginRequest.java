package com.sastit.joinus.model.request;

import javax.validation.constraints.NotBlank;

public class AuthLoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public AuthLoginRequest() {
    }

    public AuthLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
