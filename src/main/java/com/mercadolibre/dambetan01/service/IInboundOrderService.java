package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.InboundOrder;

import java.util.List;

public interface IInboundOrderService {

    InboundOrder save(InboundOrder inboundOrder);

    InboundOrder findById(Long id);

    List<InboundOrder> getAll();
}
