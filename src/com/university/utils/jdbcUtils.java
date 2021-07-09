package com.university.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author shk
 * @create 2021--04--16--20:55
 */
public class jdbcUtils {
    private static DruidDataSource dataSource;
//静态代码块给datasource赋值
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库中的连接
     * 如果返回null获取连接失败,有值就是获取连接成功
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 关闭数据库连接
     *
     * @param con
     */
    public static void close(Connection con) {
if(con!=null){
    try {
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    }
}
