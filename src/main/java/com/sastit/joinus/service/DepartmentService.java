package com.sastit.joinus.service;

import com.sastit.joinus.model.entity.Club;
import com.sastit.joinus.model.entity.Department;
import com.sastit.joinus.repository.DepartmentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ClubService clubService;

    @Inject
    public DepartmentService(DepartmentRepository departmentRepository, ClubService clubService) {
        this.departmentRepository = departmentRepository;
        this.clubService = clubService;
    }

    @Cacheable(value = "department", key = "#departmentId")
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public Department newDepartment(Long clubId, String departmentName) {
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            throw new IllegalArgumentException("社团不存在");
        }
        Department department = new Department();
        department.setName(departmentName);
        department = departmentRepository.save(department);
        club.getDepartments().add(department);
        clubService.saveClub(club);
        return department;
    }
}
