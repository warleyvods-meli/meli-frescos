package com.mercadolibre.dambetan01.util;

import com.mercadolibre.dambetan01.dtos.response.StockDueDateResponseDTO;
import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.model.Warehouse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StockTestHelper {

    public static StockDueDateResponseDTO stockDueDateToResponseDTO(){
        return StockDueDateResponseDTO.builder()
                .batchNumber(123)
                .productId(1l)
                .productType("FROZEN")
                .dueDate(LocalDate.now())
                .quantity(10)
                .build();
    }

    public static Iterable<Long> getListIterable(){
        List<Long> list = new ArrayList<>();
        list.add(1l);
        list.add(2l);
        list.add(3l);

        return list;
    }

    public static List<StockDueDateResponseDTO> ListStockDueDate(){
        List<StockDueDateResponseDTO> list = new ArrayList<>();

        list.add(StockDueDateResponseDTO.builder()
                .batchNumber(123)
                .productId(1l)
                .productType("FROZEN")
                .dueDate(LocalDate.now())
                .quantity(10)
                .build());

        list.add(StockDueDateResponseDTO.builder()
                .batchNumber(456)
                .productId(2l)
                .productType("AIRY")
                .dueDate(LocalDate.now().plusDays(2))
                .quantity(5)
                .build());

        list.add(StockDueDateResponseDTO.builder()
                .batchNumber(789)
                .productId(3l)
                .productType("FROZEN")
                .dueDate(LocalDate.now().plusDays(5))
                .quantity(8)
                .build());

        return list;
    }

    public static Section buildSection() {
        return Section.builder()
                .id(1L)
                .sectionName(StorageType.FROZEN)
                .capacity(100)
                .warehouse(null)
                .build();
    }

    public static Warehouse buildWarehouse() {

        return Warehouse.builder()
                .id(1L)
                .name("Mock")
                .sectionList(null)
                .agents(null)
                .build();
    }

    public static List<Stock> buildStockListActual() {
        ArrayList<Stock> batch_stock = new ArrayList<>();
        batch_stock.add(Stock.builder()
                .id(1L)
                .batchNumber(123)
                .orderNumber(123)
                .currentTemperature(0.0)
                .currentQuantity(10)
                .dueDate(LocalDate.now())
                .initialQuantity(10)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .minimumTemperature(0.0)
                .product(Product.builder()
                        .id(1L)
                        .productName("meat")
                        .category(StorageType.FROZEN)
                        .price(10.0)
                        .stockList(null)
                        .purchaseOrder(null)
                        .build())
                .build());

        batch_stock.add(Stock.builder()
                .id(2L)
                .batchNumber(456)
                .orderNumber(456)
                .currentTemperature(0.0)
                .currentQuantity(20)
                .dueDate(LocalDate.now().plusDays(1))
                .initialQuantity(40)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .minimumTemperature(0.0)
                .product(Product.builder()
                        .id(2L)
                        .productName("cheese")
                        .category(StorageType.AIRY)
                        .price(8.0)
                        .stockList(null)
                        .purchaseOrder(null)
                        .build())
                .build());

        return batch_stock;
    }

    public static List<Stock> buildStockListFuture() {
        ArrayList<Stock> batch_stock = new ArrayList<>();
        batch_stock.add(Stock.builder()
                .id(1L)
                .batchNumber(123)
                .orderNumber(123)
                .currentTemperature(0.0)
                .currentQuantity(10)
                .dueDate(LocalDate.now().plusDays(10))
                .initialQuantity(10)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .minimumTemperature(0.0)
                .product(Product.builder()
                        .id(1L)
                        .productName("meat")
                        .category(StorageType.FROZEN)
                        .price(10.0)
                        .stockList(null)
                        .purchaseOrder(null)
                        .build())
                .build());

        batch_stock.add(Stock.builder()
                .id(2L)
                .batchNumber(456)
                .orderNumber(456)
                .currentTemperature(0.0)
                .currentQuantity(20)
                .dueDate(LocalDate.now().plusDays(15))
                .initialQuantity(40)
                .manufacturingDate(LocalDate.now())
                .manufacturingTime(LocalDateTime.now())
                .minimumTemperature(0.0)
                .product(Product.builder()
                        .id(2L)
                        .productName("cheese")
                        .category(StorageType.AIRY)
                        .price(8.0)
                        .stockList(null)
                        .purchaseOrder(null)
                        .build())
                .build());

        return batch_stock;
    }
}
