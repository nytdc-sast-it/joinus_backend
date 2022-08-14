package com.sastit.joinus.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "t_users")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne
    @JoinColumn
    private Club club;
    @OneToOne
    @JoinColumn
    private Department department;
    @Column(nullable = false)
    private Boolean isAdmin = false;
    private Boolean pwdNeedModified = Boolean.TRUE;

    public User() {
    }

    public User(String username, String password, Club club,
                Department department, Boolean isAdmin, Boolean pwdNeedModified,
                Long id, Instant createdAt, Instant updatedAt) {
        super(id, createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.club = club;
        this.department = department;
        this.isAdmin = isAdmin;
        this.pwdNeedModified = pwdNeedModified;
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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getPwdNeedModified() {
        return pwdNeedModified;
    }

    public void setPwdNeedModified(Boolean pwdNeedModified) {
        this.pwdNeedModified = pwdNeedModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(club, user.club) && Objects.equals(department, user.department) && Objects.equals(isAdmin, user.isAdmin) && Objects.equals(pwdNeedModified, user.pwdNeedModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, club, department, isAdmin, pwdNeedModified);
    }
}
