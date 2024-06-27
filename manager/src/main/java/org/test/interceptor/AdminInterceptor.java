package org.test.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.test.po.UserInfo;
import org.test.util.Base64Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userInfoJson = request.getHeader("userInfo");
        UserInfo userInfo = JSON.parseObject(Base64Util.decode(userInfoJson), UserInfo.class);
        if(!userInfo.getRole().equals("admin")) {
            JSONObject result = new JSONObject();
            result.put("code",100001);
            result.put("errMsg","对不起，你没有权限!");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(result.toString());
            writer.flush();
            writer.close();
            return false;
        }
        return true;
    }
}
