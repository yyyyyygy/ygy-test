package org.test.controller;


import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{resource}")
    public String gerResource(@PathVariable("resource") String resource){
        JSONObject result = new JSONObject();
        result.put("code",200);
        result.put("msg","success");
        return result.toString();
    }

}
