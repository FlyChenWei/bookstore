package com.university.service.impl;

import com.university.pojo.Cart;

/**
 * @author shk
 * @create 2021--05--13--18:08
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
