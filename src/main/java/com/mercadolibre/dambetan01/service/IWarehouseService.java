package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.model.Warehouse;

import java.util.List;

public interface IWarehouseService{

    Warehouse save(Warehouse warehouse);

    Warehouse findById(Long id);

    List<Warehouse> getAll();
}
