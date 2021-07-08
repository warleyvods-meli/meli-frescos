package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.service.IStockService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final SectionRepository sectionRepository;

    public StockServiceImpl(StockRepository stockRepository, SectionRepository sectionRepository) {
        this.stockRepository = stockRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List <Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock findById(Long id) {
        return stockRepository.findById(id).orElseThrow(()-> new NotFoundException("Stock "+id+ " not found."));
    }

    @Override
    public Stock findByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

    @Override
    public List<Stock> findStockFromSectionDueDate(Long id, Long day) {
        Iterable<Long> stockListId = sectionRepository.findStockList(id);
        List<Stock> stockList = stockRepository.findAllById(stockListId);
        List<Stock> collect = stockList.stream()
                .filter(list -> list.getDueDate().isBefore(ChronoLocalDate.from(LocalDate.now().plusDays(day))))
                .sorted(Comparator.comparing(Stock::getDueDate))
                .collect(Collectors.toList());

        return collect;
    }

}
