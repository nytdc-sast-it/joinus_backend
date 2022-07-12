package org.tdsast.joinus.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response<AuthLoginResponseData> login(@RequestBody @Valid AuthLoginRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(request.getUsername(),
                AuthUtils.getEnPsssword(request.getUsername(), request.getPassword()));
        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            return Response.failure(null, "账号或密码错误", 50000);
        }
        return Response.success(new AuthLoginResponseData(null));
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
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            return Response.failure(null, "未登录", 50000);
        }
        User user = userService.getUserByUsername(currentUser.getPrincipal().toString());
        return Response.success(new CurrentUserResponseData(user.getId(), user.getUsername(),
                user.getIsAdmin(), clubToClubDTO(user.getClub())));
    }
}
