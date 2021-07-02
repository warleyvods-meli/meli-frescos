package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.controller.InboundOrderController;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.InboundOrderRepository;
import com.mercadolibre.dambetan01.service.IInboundOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InboundOrderServiceImpl implements IInboundOrderService {

    private final InboundOrderRepository inboundOrderRepository;
    private final SectionServiceImpl sectionService;
    private final WarehouseServiceImpl warehouseService;
    private final StockServiceImpl stockService;

    public InboundOrderServiceImpl(InboundOrderRepository inboundOrderRepository, SectionServiceImpl sectionService, WarehouseServiceImpl warehouseService, StockServiceImpl stockService) {
        this.inboundOrderRepository = inboundOrderRepository;
        this.sectionService = sectionService;
        this.warehouseService = warehouseService;
        this.stockService = stockService;
    }

    @Override
    public InboundOrder createInboundOrder(InboundOrder inboundOrder) {
        InboundOrder inboundOrder1 = InboundOrder.builder()
                .orderDate(inboundOrder.getOrderDate())
                .section(sectionService.findById(inboundOrder.getSection().getId()))
                .batchStock(this.findStockList(inboundOrder))
                .build();
        inboundOrderRepository.save(inboundOrder1);
        inboundOrderRepository.findById(inboundOrder1.getId());
        return inboundOrder1;
    }

    private List<Stock> findStockList(InboundOrder inboundOrder){
        List<Stock> stockList = new ArrayList();
        for(Stock s : inboundOrder.getBatchStock()){
             stockList.add(stockService.findById(s.getId()));
        }
        return stockList;
    }

    private void updateStockInboundOrder(Long id){

    }
}
