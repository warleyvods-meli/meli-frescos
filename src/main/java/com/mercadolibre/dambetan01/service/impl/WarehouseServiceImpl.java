package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.Warehouse;
import com.mercadolibre.dambetan01.repository.WareHouseRepository;
import com.mercadolibre.dambetan01.service.IWarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    public WarehouseServiceImpl(WareHouseRepository repository) {
        this.repository = repository;
    }

    private final WareHouseRepository repository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        return repository.save(warehouse);
    }

    @Override
    public Warehouse findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Warehouse not found."));
    }

    @Override
    public List <Warehouse> getAll() {
        return repository.findAll();
    }
}
