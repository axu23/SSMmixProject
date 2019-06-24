package com.itcast.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.domain.Orders;
import com.itcast.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;
    /*未分页*/
  /*  @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Orders> all = ordersService.findAll();
        System.out.println(all);
        ModelAndView mv = new ModelAndView();
        mv.addObject("ordersList", all);
        mv.setViewName("orders-list");
        return mv;
    }*/

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page,
                                @RequestParam(name = "size", required = true, defaultValue = "1") int size
    ) throws Exception {

        List<Orders> all = ordersService.findAll(page, size);
        PageInfo<Orders> info = new PageInfo<>(all, 6);

        System.out.println(all);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", info);
        mv.setViewName("orders-list");
        return mv;
    }

    /*orders/findById.do?id=${orders.id}*/
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        System.out.println(id);
        Orders orders = ordersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
