package com.itcast.dao;

import com.itcast.domain.Member;
import com.itcast.domain.Orders;
import com.itcast.domain.Product;
import com.itcast.domain.Traveller;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product", column = "productid", javaType = Product.class, one = @One(select = "com.itcast.dao.ProductDao.findOne")),
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "member", column = "memberid", javaType = Member.class, one = @One(select = "com.itcast.dao.MemberDao.findById")),
            @Result(property = "product", column = "productid", javaType = Product.class, one = @One(select = "com.itcast.dao.ProductDao.findOne")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itcast.dao.TravellerDao.findById"))
    })
    Orders findById(String ordersId) throws Exception;
}
