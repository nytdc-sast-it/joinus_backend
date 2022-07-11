package org.tdsast.joinus.service;

import org.springframework.stereotype.Service;
import org.tdsast.joinus.repository.DepartmentRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
}
