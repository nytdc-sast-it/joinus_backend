package org.tdsast.joinus.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.model.dto.UserDTO;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.model.request.NewUserRequest;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.model.response.UserListResponseData;
import org.tdsast.joinus.model.response.UserResponseData;
import org.tdsast.joinus.service.ClubService;
import org.tdsast.joinus.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ClubService clubService;

    @Inject
    public UserController(UserService userService, ClubService clubService) {
        this.userService = userService;
        this.clubService = clubService;
    }

    private UserDTO userToUserDTO(User user) {
        Club club = user.getClub();
        if (club == null) {
            return new UserDTO(user.getId(), user.getUsername(), null, user.getIsAdmin());
        }
        return new UserDTO(user.getId(), user.getUsername(), user.getClub().getName(), user.getIsAdmin());
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

    @PostMapping("/new")
    public Response<UserResponseData> newUser(@RequestBody @Valid NewUserRequest request) {
        User user = userService.addUser(request.getUsername(), request.getPassword(), clubIdToClub(request.getClubId()), request.getAdmin());
        return Response.success(new UserResponseData(userToUserDTO(user)));
    }

    @DeleteMapping("/{userId}")
    public Response<UserResponseData> removeClub(@PathVariable Long userId) {
        userService.removeUser(userId);
        return Response.success(new UserResponseData(null));
    }
}
