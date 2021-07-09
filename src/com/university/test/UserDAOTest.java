package com.university.test;

import com.university.dao.impl.UserDaoImpl;
import com.university.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shk
 * @create 2021--04--17--16:25
 */
public class UserDAOTest {

    @Test
    public void queryUserByUseName() {
        UserDaoImpl dao = new UserDaoImpl();
        User admin = dao.queryUserByUseName("admin");
        System.out.println(admin);
    }

    @Test
    public void queryUserByNameAndPasswd() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByNameAndPasswd("cw", "cw");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(3, "F", "F", "F");
        int i = userDao.saveUser(user);
        System.out.println(i);
    }
}