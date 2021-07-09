package com.university.dao.impl;

import com.university.pojo.User;

/**
 * @author shk
 * @create 2021--04--17--15:55
 */
public  interface UserDAO {
    /**
     * 根据用户名查询用户信息
     * @param name 用户名
     * @return 如果返回null,说明没有该用户,
     */
    public User queryUserByUseName(String name);
    public User queryUserByNameAndPasswd(String name,String passwd);
    public int saveUser(User user);
}
