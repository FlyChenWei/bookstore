package com.university.dao.impl;

import com.university.pojo.User;

/**
 * @author shk
 * @create 2021--04--17--16:02
 */
public class UserDaoImpl extends BaseDAO implements UserDAO {

    @Override
    public User queryUserByUseName(String name) {
        String sql="SELECT id,username,PASSWORD,email \n" +
                "FROM t_user\n" +
                "WHERE username=?";
        return queryForOne(User.class,sql,name);
    }

    @Override
    public User queryUserByNameAndPasswd(String name, String passwd) {
        String sql="SELECT id,username,PASSWORD,email \n" +
                "FROM t_user\n" +
                "WHERE username=? and password=?";
        return queryForOne(User.class,sql,name,passwd);
    }

    @Override
    public int saveUser(User user) {
        String sql="INSERT INTO t_user(`username`,`password`,`email`) VALUE(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
