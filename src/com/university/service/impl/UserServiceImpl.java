package com.university.service.impl;

import com.university.dao.impl.UserDaoImpl;
import com.university.pojo.User;

/**
 * @author shk
 * @create 2021--04--17--17:21
 */
public class UserServiceImpl implements UserService {
    private UserDaoImpl dao=new UserDaoImpl();
    @Override
    public void registUser(User user) {
          dao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return dao.queryUserByNameAndPasswd(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String name) {
        User user = dao.queryUserByUseName(name);
        if(user==null) return false;
        return true;
    }
}
