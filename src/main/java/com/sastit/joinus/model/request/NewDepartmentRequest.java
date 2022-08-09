package com.sastit.joinus.model.request;

public class NewDepartmentRequest {
    private String name;
    private Long clubId;

    public NewDepartmentRequest() {
    }

    public NewDepartmentRequest(String name, Long clubId) {
        this.name = name;
        this.clubId = clubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }
}
