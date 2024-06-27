package org.test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.po.AuthInfo;
import org.test.service.AdminService;

import javax.annotation.Resource;

import java.util.ArrayList;


@SpringBootTest
class AdminControllerTest {
    @Resource
    AdminService adminService;

    @Test
    public void test() {
        ArrayList<AuthInfo> authInfoList = new ArrayList<>();
        authInfoList.add(new AuthInfo(123456L, "resource A", 1));
        adminService.addUser(authInfoList);
    }
}