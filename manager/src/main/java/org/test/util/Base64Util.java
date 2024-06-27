package org.test.util;

import com.alibaba.fastjson.JSON;
import org.test.po.UserInfo;

import java.util.Base64;

public class Base64Util {
    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String decode(String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        return new String(bytes);
    }

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(123456L);
        userInfo.setRole("user");
        userInfo.setAccountName("123456");
        System.out.println(encode(JSON.toJSONString(userInfo)));
        System.out.println(decode(encode(JSON.toJSONString(userInfo))));

    }
}
