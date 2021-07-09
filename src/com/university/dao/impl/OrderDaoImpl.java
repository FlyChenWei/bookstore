package com.university.dao.impl;

import com.university.pojo.Order;

/**
 * @author shk
 * @create 2021--05--13--17:29
 */
public class OrderDaoImpl  extends BaseDAO implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateDate(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
