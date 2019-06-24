package com.itcast.service;


import com.itcast.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll() throws Exception;

    void saveProduct(Product product) throws Exception;
}
