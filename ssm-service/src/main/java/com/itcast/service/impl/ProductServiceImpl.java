package com.itcast.service.impl;

import com.itcast.dao.ProductDao;
import com.itcast.domain.Product;
import com.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    public ProductServiceImpl() {
        System.out.println("service初始化了！！！！！！！！！！");
    }

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public void saveProduct(Product product) throws Exception {
        productDao.saveProduct(product);
    }
}
