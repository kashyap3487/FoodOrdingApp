package com.example.ass45;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private static List<Order> orderList;
    public static List<Order> getInstance() {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        return orderList;
    }
}
