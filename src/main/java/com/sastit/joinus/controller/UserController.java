package com.sastit.joinus.controller;

import com.sastit.joinus.model.dto.UserDTO;
import com.sastit.joinus.model.entity.Club;
import com.sastit.joinus.model.entity.Department;
import com.sastit.joinus.model.entity.User;
import com.sastit.joinus.model.request.NewUserRequest;
import com.sastit.joinus.model.response.Response;
import com.sastit.joinus.model.response.UserListResponseData;
import com.sastit.joinus.model.response.UserResponseData;
import com.sastit.joinus.service.ClubService;
import com.sastit.joinus.service.DepartmentService;
import com.sastit.joinus.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ClubService clubService;
    private final DepartmentService departmentService;

    @Inject
    public UserController(UserService userService, ClubService clubService, DepartmentService departmentService) {
        this.userService = userService;
        this.clubService = clubService;
        this.departmentService = departmentService;
    }

    private UserDTO userToUserDTO(User user) {
        Club club = user.getClub();
        Department department = user.getDepartment();
        if (club == null) {
            return new UserDTO(user.getId(), user.getUsername(), null, null, user.getIsAdmin());
        }
        if (department == null) {
            return new UserDTO(user.getId(), user.getUsername(), club.getName(), null, user.getIsAdmin());
        }
        return new UserDTO(user.getId(), user.getUsername(), club.getName(), department.getName(), user.getIsAdmin());
    }

    @GetMapping("/list")
    public Response<UserListResponseData> list() {
        List<UserDTO> list = userService.getUsers().stream().map(this::userToUserDTO).collect(Collectors.toList());
        return Response.success(new UserListResponseData(list));
    }

    private Club clubIdToClub(Long clubId) {
        if (clubId == null) {
            return null;
        }
        Club club = clubService.getClubById(clubId);
        if (club == null) {
            throw new IllegalArgumentException("社团不存在");
        }
        return club;
    }

    private Department departmentIdToDepartment(Club club, Long departmentId) {
        if (club == null || departmentId == null) {
            return null;
        }
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null || !club.getDepartments().contains(department)) {
            throw new IllegalArgumentException("社团部门不存在");
        }
        return department;
    }

    @PostMapping("/new")
    public Response<UserResponseData> newUser(@RequestBody @Valid NewUserRequest request) {
        Club club = clubIdToClub(request.getClubId());
        Department department = departmentIdToDepartment(club, request.getDepartmentId());
        User user = userService.addUser(request.getUsername(), request.getPassword(), club, department, request.getAdmin());
        return Response.success(new UserResponseData(userToUserDTO(user)));
    }

    @DeleteMapping("/{userId}")
    public Response<UserResponseData> removeClub(@PathVariable Long userId) {
        userService.removeUser(userId);
        return Response.success(new UserResponseData(null));
    }
}
