package com.itcast.controller;

import com.itcast.domain.Product;
import com.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> all = productService.findAll();
        mv.addObject("productList", all);
        mv.setViewName("product-list1");
        System.out.println("请求到达了");
        return mv;
    }

    @RequestMapping(value = "/save.do")//produces是设置响应乱码问题
    public @ResponseBody
    String saveProduct(@RequestBody Product product) {
        System.out.println(product);
        String flag = "success";
        try {
            productService.saveProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            // 存储异常提示前端跳转到异常界面
            flag = "error";
        }
        System.out.println(flag);
        return flag;
    }
}
