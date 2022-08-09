package com.sastit.joinus.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CandidateRequest {
    @NotBlank
    private String name;
    @Size(min = 8, max = 8, message = "学号长度必须为8位")
    private String studentId;
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号码格式不正确")
    private String phone;
    @Size(min = 5, max = 15, message = "QQ号码长度必须为5-15位")
    private String qq;
    @NotBlank
    private String major;
    @NotBlank
    private String counselor;
    private Long club;
    private Long choice1;
    private Long choice2;
    private String reason;

    public CandidateRequest() {
    }

    public CandidateRequest(String name, String studentId, String phone, String qq, String major,
                            String counselor, Long club, Long choice1, Long choice2, String reason) {
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

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public Long getClub() {
        return club;
    }

    public void setClub(Long club) {
        this.club = club;
    }

    public Long getChoice1() {
        return choice1;
    }

    public void setChoice1(Long choice1) {
        this.choice1 = choice1;
    }

    public Long getChoice2() {
        return choice2;
    }

    public void setChoice2(Long choice2) {
        this.choice2 = choice2;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
