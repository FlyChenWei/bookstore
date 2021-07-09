package com.university.test;

import com.university.pojo.User;
import com.university.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author shk
 * @create 2021--04--17--17:32
 */
public class UserServiceTest {

    @Test
    public void registUser() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.registUser(new User(4,"cw3","cw","cw"));
    }

    @Test
    public void login() {
        UserServiceImpl userService = new UserServiceImpl();
        User login = userService.login(new User(4, "cw3", "cw", "cw"));
        System.out.println(login);

    }

    @Test
    public void existUsername() {
        UserServiceImpl userService = new UserServiceImpl();
        boolean cw3 = userService.existUsername("cw3");
        System.out.println(cw3);
    }
}