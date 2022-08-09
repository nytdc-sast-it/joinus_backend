package com.sastit.joinus.model.response;

import com.sastit.joinus.model.dto.ClubDTO;
import com.sastit.joinus.model.dto.DepartmentDTO;

import java.io.Serializable;

public class CurrentUserResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private Boolean admin;
    private String role;
    private ClubDTO club;
    private DepartmentDTO department;
    private String avatar = "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png";

    public CurrentUserResponseData(Long id, String username, Boolean admin, ClubDTO club, DepartmentDTO department) {
        this.id = id;
        this.username = username;
        this.admin = admin;
        this.club = club;
        this.department = department;
        this.role = Boolean.TRUE.equals(admin) ? "admin" : "user";
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ClubDTO getClub() {
        return club;
    }

    public void setClub(ClubDTO club) {
        this.club = club;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
