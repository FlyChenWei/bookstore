package com.university.dao.impl;


import com.alibaba.druid.util.JdbcUtils;
import com.university.utils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author shk
 * @create 2021--04--17--14:43
 */
//复用代码
public abstract class BaseDAO {
    private QueryRunner runner = new QueryRunner();
    //使用dbutils操作数据库

    /**
     * update()方法用来执行insert update delete
     */
    public int update(String sql, Object... args) {
        Connection con = null;
        try {
            con = jdbcUtils.getConnection();
            int update = runner.update(con, sql, args);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(con);
        }
        return -1;
    }

    /**
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return 返回单个bean对象
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection con = jdbcUtils.getConnection();
        try {
            T query = runner.query(con, sql, new BeanHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(con);
        }
        return null;
    }

    /**
     *
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = jdbcUtils.getConnection();
        try {
            List<T> query =  runner.query(con, sql, new BeanListHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(con);
        }
        return null;
    }

    /**
     * @param sql
     * @param args
     * @return
     */
    public Object queryForValue(String sql,Object ...args ){
        Connection con = jdbcUtils.getConnection();
        try {
            Object query = runner.query(sql, new ScalarHandler(), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.close(con);
        }
        return null;
    }
    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args){

        Connection conn = jdbcUtils.getConnection();

        try {
            return runner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;

    }

}
