package org.test.service;

import org.springframework.stereotype.Component;
import org.test.dao.UserDao;
import org.test.po.AuthInfo;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AdminService {
    @Resource
    UserDao userDao;

    public Boolean addUser(List<AuthInfo> authInfoList) {
        return userDao.insert(authInfoList) ;
    }
}
