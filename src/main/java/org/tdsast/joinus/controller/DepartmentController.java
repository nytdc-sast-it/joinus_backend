package org.tdsast.joinus.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.model.dto.DepartmentDTO;
import org.tdsast.joinus.model.entity.Department;
import org.tdsast.joinus.model.request.NewDepartmentRequest;
import org.tdsast.joinus.model.response.DepartmentResponseData;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.service.DepartmentService;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;


    @Inject
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private DepartmentDTO departmentToDepartmentDTO(Department department) {
        return new DepartmentDTO(department.getId(), department.getName());
    }

    @PostMapping("/new")
    public Response<DepartmentResponseData> newDepartment(@RequestBody @Valid NewDepartmentRequest request) {
        Department department = departmentService.newDepartment(request.getClubId(), request.getName());
        return Response.success(new DepartmentResponseData(departmentToDepartmentDTO(department)));
    }
}
