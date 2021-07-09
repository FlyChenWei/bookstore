package com.university.dao.impl;

import com.university.pojo.Order;
import com.university.pojo.OrderItem;

/**
 * @author shk
 * @create 2021--05--13--17:29
 */
public class OrderItemDaoImpl extends
        BaseDAO implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
//        String sql="insert into t_order_item('name','count','price','total_price','order_id') values(?,?,?,?,?)";
//        return update(sql,item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),
//                item.getOrderId());
    }
}
