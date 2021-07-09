package com.university.service.impl;

import com.university.pojo.User;

/**
 * @author shk
 * @create 2021--04--17--17:13
 */
public interface UserService {
    /**
     * 注册用户业务
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录业务
     * @param user
     */
    public User login(User user);

    /**
     * 检测用户名是否可用
     * @param name
     * @return 返回true 已经存在,false 用户名可用
     */
    public boolean existUsername(String name);
}
