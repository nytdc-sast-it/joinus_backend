package org.tdsast.joinus.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Department;
import org.tdsast.joinus.repository.DepartmentRepository;

import javax.inject.Inject;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Inject
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }
}
