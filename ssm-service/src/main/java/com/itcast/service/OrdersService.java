package com.itcast.service;

import com.itcast.domain.Orders;

import java.util.List;

public interface OrdersService {

    /*List<Orders> findAll() throws Exception;*/
    /*分页*/
    List<Orders> findAll(int page,int size) throws Exception;

    /*通过id查询订单详情*/
    Orders findById(String ordersId) throws Exception;
}
