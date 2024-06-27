package org.test.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.test.po.AuthInfo;
import org.test.service.AdminService;
import org.test.vo.AddUserVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @PostMapping("/addUser")
    public JSONObject addUser(@RequestBody AddUserVO addUserVO) {
        List<AuthInfo> authInfoList = addUserVO.getEndpoint().stream().map(item -> new AuthInfo(addUserVO.getUserId(), item, 1)).collect(Collectors.toList());
        JSONObject result = new JSONObject();
        result.put("code",200);
        result.put("msg",adminService.addUser(authInfoList));
        return result;
    }
}
