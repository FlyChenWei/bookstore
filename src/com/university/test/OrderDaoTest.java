package com.university.test;

import com.university.dao.impl.OrderDao;
import com.university.dao.impl.OrderDaoImpl;
import com.university.dao.impl.OrderItemDao;
import com.university.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author shk
 * @create 2021--05--13--17:48
 */
public class OrderDaoTest {
private OrderDao dao=new OrderDaoImpl();
    @Test
    public void saveOrder() {
        dao.saveOrder(new Order("1",
                new Date(),new BigDecimal(999),1,1));
    }
}