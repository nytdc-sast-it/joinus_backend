package com.sastit.joinus.model.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String club;
    private String department;
    private boolean admin;
    private Boolean pwdNeedModified;

    public UserDTO(Long id, String username, String club, String department, boolean admin,Boolean pwdNeedModified) {
        this.id = id;
        this.username = username;
        this.club = club;
        this.department = department;
        this.admin = admin;
        this.pwdNeedModified = pwdNeedModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Boolean getPwdNeedModified() {
        return pwdNeedModified;
    }

    public void setPwdNeedModified(Boolean pwdNeedModified) {
        this.pwdNeedModified = pwdNeedModified;
    }
}
