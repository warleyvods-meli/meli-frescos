package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.Buyer;
import com.mercadolibre.dambetan01.repository.BuyerRepository;
import com.mercadolibre.dambetan01.service.IBuyerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements IBuyerService {

    private final BuyerRepository repository;

    public BuyerServiceImpl(BuyerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Buyer save(Buyer buyer) {
        return repository.save(buyer);
    }

    @Override
    public Buyer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Buyer "+id+ " not found"));
    }

    @Override
    public List <Buyer> getAll() {
        return repository.findAll();
    }
}
