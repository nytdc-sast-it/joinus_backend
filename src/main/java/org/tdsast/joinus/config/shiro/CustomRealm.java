package org.tdsast.joinus.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.service.UserService;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户名
        String username = (String) principals.getPrimaryPrincipal();
        // 根据用户名查询用户信息
        User user = userService.getUserByUsername(username);
        // 创建权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (Boolean.TRUE.equals(user.getIsAdmin())) {
            authorizationInfo.addRole("admin");
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (ObjectUtils.isEmpty(token.getPrincipal())) {
            return null;
        }
        // 获取用户信息
        String username = (String) token.getPrincipal();
        User user = userService.getUserByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        } else {
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(), getName());
        }
    }
}
