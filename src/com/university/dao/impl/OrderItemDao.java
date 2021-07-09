package com.university.dao.impl;

import com.university.pojo.Order;
import com.university.pojo.OrderItem;

/**
 * @author shk
 * @create 2021--05--13--17:27
 */
public interface  OrderItemDao {
    public int saveOrderItem(OrderItem item);
}
