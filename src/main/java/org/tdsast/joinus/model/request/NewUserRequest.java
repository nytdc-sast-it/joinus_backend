package org.tdsast.joinus.model.request;

import javax.validation.constraints.NotBlank;

public class NewUserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Long clubId;
    private Long departmentId;
    private Boolean admin;

    public NewUserRequest() {
    }

    public NewUserRequest(String username, String password, Long clubId, Long departmentId, Boolean admin) {
        this.username = username;
        this.password = password;
        this.clubId = clubId;
        this.departmentId = departmentId;
        this.admin = admin;
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

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
