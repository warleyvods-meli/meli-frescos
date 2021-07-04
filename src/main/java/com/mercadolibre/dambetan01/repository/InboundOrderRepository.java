package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Buyer;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    @Query(value = "SELECT * FROM STOCK INNER JOIN INBOUND_ORDER ON INBOUND_ORDER.order_number = STOCK.order_number", nativeQuery = true)
    List<Stock> fillStockList(@Param("order_number") int inboundOrderNumber);
}

