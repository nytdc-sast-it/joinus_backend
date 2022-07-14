package org.tdsast.joinus.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tdsast.joinus.config.security.JWTUtils;
import org.tdsast.joinus.model.dto.ClubDTO;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.model.request.AuthLoginRequest;
import org.tdsast.joinus.model.response.AuthLoginResponseData;
import org.tdsast.joinus.model.response.CurrentUserResponseData;
import org.tdsast.joinus.model.response.Response;
import org.tdsast.joinus.model.response.ResponseData;
import org.tdsast.joinus.service.UserService;
import org.tdsast.joinus.utils.AuthUtils;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @Inject
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Response<AuthLoginResponseData> login(@RequestBody @Valid AuthLoginRequest request) {
        // 查询用户信息
        User user = userService.getUserByUsername(request.getUsername());
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }
        // 校验密码
        String password = AuthUtils.getEnPassword(request.getUsername(), request.getPassword());
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("密码错误");
        }
        // 生成token
        String token = JWTUtils.createToken(request.getUsername());
        // 返回结果
        return Response.success(new AuthLoginResponseData(token));
    }

    @PostMapping("/logout")
    public Response<ResponseData> logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
            return Response.success(null);
        } else {
            return Response.failure(null, "未登录", 50000);
        }
    }

    private ClubDTO clubToClubDTO(Club club) {
        if (club == null) {
            return null;
        }
        return new ClubDTO(club.getId(), club.getName());
    }

    @GetMapping("/current")
    public Response<CurrentUserResponseData> currentUser() {
        String currentUsername = JWTUtils.getUsernameFromToken((String) SecurityUtils.getSubject().getPrincipal());
        User user = userService.getUserByUsername(currentUsername);
        return Response.success(new CurrentUserResponseData(user.getId(), user.getUsername(),
            user.getIsAdmin(), clubToClubDTO(user.getClub())));
    }
}
