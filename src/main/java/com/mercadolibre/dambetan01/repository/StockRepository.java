package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
