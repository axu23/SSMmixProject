package com.itcast.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.dao.OrderDao;
import com.itcast.domain.Orders;
import com.itcast.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrderDao orderDao;

    /*    @Override
        public List<Orders> findAll() throws Exception {
            List<Orders> all = orderDao.findAll();
            return all;
        }*/

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        List<Orders> all = orderDao.findAll();
        return all;
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        Orders orders=orderDao.findById(ordersId);
        return orders;
    }
}
