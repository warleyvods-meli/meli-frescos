package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.repository.InboundOrderRepository;
import com.mercadolibre.dambetan01.service.IInboundOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundOrderServiceImpl implements IInboundOrderService {

    public InboundOrderServiceImpl(InboundOrderRepository repository) {
        this.repository = repository;
    }

    private final InboundOrderRepository repository;

    @Override
    public InboundOrder save(InboundOrder inboundOrder) {
        return repository.save(inboundOrder);
    }

    @Override
    public InboundOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Inbound order " + id + " not found"));
    }

    @Override
    public List <InboundOrder> getAll() {
        return null;
    }
}
