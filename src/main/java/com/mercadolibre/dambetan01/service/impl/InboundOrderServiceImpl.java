package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.InboundOrderRepository;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.service.IInboundOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundOrderServiceImpl implements IInboundOrderService {

    private final InboundOrderRepository repository;
    private final StockRepository stockRepository;
    private final SectionRepository sectionRepository;

    public InboundOrderServiceImpl(InboundOrderRepository repository, StockRepository stockRepository, SectionRepository sectionRepository) {
        this.repository = repository;
        this.stockRepository = stockRepository;
        this.sectionRepository = sectionRepository;
    }

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
        return repository.findAll();
    }

    @Override
    public InboundOrder saveWithChild(InboundOrder order) {
        List<Stock> stockList = order.getBatchStock();
        Section section = decreaseSectionSpace(order);
        sectionRepository.save(section);
        stockRepository.saveAll(stockList);
        return repository.save(order);
    }

    private Section decreaseSectionSpace(InboundOrder order) {
        Section section = sectionRepository.findById(order.getSection().getId()).orElseThrow(() -> new NotFoundException("section not found"));
        var neededSpace = order.getBatchStock().stream().mapToInt(Stock::getCurrentQuantity).sum();
        section.setCapacity(section.getCapacity() -  neededSpace );
        return section;
    }

    public List<Stock> fillStockList(int inboundOrderNumber){
        return repository.fillStockList(inboundOrderNumber);
    }

}
