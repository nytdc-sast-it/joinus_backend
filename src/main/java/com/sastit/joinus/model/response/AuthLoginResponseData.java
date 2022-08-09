package com.sastit.joinus.model.response;

import java.io.Serializable;

public class AuthLoginResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    public AuthLoginResponseData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
