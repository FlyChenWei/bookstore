package com.university.test;

import com.university.pojo.Cart;
import com.university.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author shk
 * @create 2021--05--11--20:52
 */
public class CartTest {
     Cart cart=new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"javaEE",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(1,"javaEE",1,new BigDecimal(10),new BigDecimal(10)));
        cart.addItem(new CartItem(2,"javase",2,new BigDecimal(100),new BigDecimal(100)));

        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void updateCount() {
    }
}