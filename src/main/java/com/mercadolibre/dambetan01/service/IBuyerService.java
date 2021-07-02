package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Buyer;

import java.util.List;

public interface IBuyerService {
    Buyer save(Buyer buyer);

    Buyer findById(Long id);

    List <Buyer> getAll();
}
