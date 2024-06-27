package org.test.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.test.dao.UserDao;
import org.test.po.UserInfo;
import org.test.util.Base64Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Resource
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userInfoJson = request.getHeader("userInfo");
        UserInfo userInfo = JSON.parseObject(Base64Util.decode(userInfoJson), UserInfo.class);
         if(userInfo.getRole().equals("user") && checkAuth(userInfo.getUserId(), request.getRequestURI())){
             return true;
         }else {
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
    }

    private boolean checkAuth(Long userId, String uri) {
        String[] split = uri.split("/");
        return userDao.hasAuth(userId, URLDecoder.decode(split[split.length-1]));
    }
}
