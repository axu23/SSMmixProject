package com.itcast.dao;

import com.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderid=#{orderId})")
    public List<Traveller> findById(String orderId)throws Exception;
}
