package com.university.service.impl;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.university.dao.impl.*;
import com.university.pojo.*;

import java.util.Date;
import java.util.Map;

/**
 * @author shk
 * @create 2021--05--13--18:09
 */
public class OrderServiceImpl implements  OrderService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private OrderDao orderDao=new OrderDaoImpl();
     private BookDao bookDao= new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号 唯一性
        String orderId=System.currentTimeMillis()+"";
        //创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中的每一个商品项转换为订单项保存进数据库
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
                    cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            //修改库存销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();
        return orderId;
    }
}
