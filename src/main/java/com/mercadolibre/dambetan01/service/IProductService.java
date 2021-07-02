package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Product;

import java.util.List;

public interface IProductService {

    Product save(Product product);

    Product findById(Long id);

    List<Product> getAll();
}
