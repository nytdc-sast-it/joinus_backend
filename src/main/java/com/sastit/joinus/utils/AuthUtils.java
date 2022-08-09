package com.sastit.joinus.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class AuthUtils {

    private AuthUtils() {
    }

    public static String getEnPassword(String username, String password) {
        String hashAlgorithmName = "MD5"; // 加密方式：md5加密
        Object credentials = password; // 密码
        Object salt = ByteSource.Util.bytes(username);// 盐
        int hashIterations = 1024; // 加密次数
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result.toString();
    }
}
