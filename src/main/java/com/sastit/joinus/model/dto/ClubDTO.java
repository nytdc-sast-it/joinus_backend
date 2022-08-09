package com.sastit.joinus.model.dto;

import java.io.Serializable;
import java.util.List;

public class ClubDTO implements Serializable {
    private Long id;
    private String name;
    private List<DepartmentDTO> departments;
    private String type;

    public ClubDTO(Long id, String name, List<DepartmentDTO> departments) {
        this.id = id;
        this.name = name;
        this.departments = departments;
        this.type = "club";
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

    public List<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentDTO> departments) {
        this.departments = departments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
