package com.sastit.joinus.model.response;

import com.sastit.joinus.model.dto.DepartmentDTO;

import java.io.Serializable;

public class DepartmentResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private DepartmentDTO department;

    public DepartmentResponseData(DepartmentDTO department) {
        this.department = department;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }
}
