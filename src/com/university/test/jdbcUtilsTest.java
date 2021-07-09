package com.university.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.university.utils.jdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author shk
 * @create 2021--04--16--21:14
 */
public class jdbcUtilsTest {
@Test
    public void test(){
    Connection connection = jdbcUtils.getConnection();
    System.out.println(connection);

}
}
