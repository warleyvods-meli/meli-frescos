package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;

import java.util.List;

public interface IStockService {

    Stock save(Stock stock);

    Stock findById(Long id);

    List<Stock> getAll();

    Stock findByProductId(Long productId);

}
