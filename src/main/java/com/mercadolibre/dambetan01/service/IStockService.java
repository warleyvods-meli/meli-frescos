package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Stock;

import java.util.List;

public interface IStockService {
    List<Stock> getAll();

    Stock findById(Long id);
}
