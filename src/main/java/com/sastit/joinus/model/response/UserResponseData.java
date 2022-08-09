package com.sastit.joinus.model.response;

import com.sastit.joinus.model.dto.UserDTO;

import java.io.Serializable;

public class UserResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserDTO user;

    public UserResponseData(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
