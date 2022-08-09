package com.sastit.joinus.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public class CandidateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String studentId;
    private String phone;
    private String qq;
    private String major;
    private String counselor;
    private String club;
    private String choice1;
    private String choice2;
    private String reason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8",
        pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createdTime;

    public CandidateDTO(Long id, String name, String studentId, String phone, String qq,
                        String major, String counselor, String club, String choice1, String choice2,
                        String reason, Instant createdTime) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.phone = phone;
        this.qq = qq;
        this.major = major;
        this.counselor = counselor;
        this.club = club;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.reason = reason;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }
}
