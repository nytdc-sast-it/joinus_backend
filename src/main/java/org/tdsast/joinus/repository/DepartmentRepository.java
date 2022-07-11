package org.tdsast.joinus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tdsast.joinus.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
