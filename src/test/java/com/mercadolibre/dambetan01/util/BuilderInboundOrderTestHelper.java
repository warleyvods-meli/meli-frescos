package com.mercadolibre.dambetan01.util;

import com.mercadolibre.dambetan01.dtos.request.InboundOrderRequest;
import com.mercadolibre.dambetan01.dtos.request.SectionRequest;
import com.mercadolibre.dambetan01.dtos.request.StockRequest;
import com.mercadolibre.dambetan01.enums.StorageType;
import com.mercadolibre.dambetan01.enums.UserType;
import com.mercadolibre.dambetan01.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BuilderInboundOrderTestHelper {

    public static InboundOrder getInboundOrder(){
     var order = buildInboundOrder();
     var section = buildSection();
     var warehouse = buildWarehouse();
     var batch_stock = buildStockList();
     var agent = buildAgent();

     agent.setInboundOrder(order);
     agent.setWarehouse(warehouse);
     section.setWarehouse(warehouse);
     batch_stock.get(0).setSection(section);
     batch_stock.get(0).getProduct().setStockList(batch_stock);
     section.setStockList(batch_stock);
     List<Section> sections = new ArrayList <>();
     sections.add(section);
     List<Agent> agents = new ArrayList <>();
     agents.add(agent);
     warehouse.setAgents(agents);
     warehouse.setSectionList(sections);
     order.setAgent(agent);
     order.setSection(section);
     order.setBatchStock(batch_stock);
     return order;

    }

    public static InboundOrderRequest getInboundOrderRequest(){
        return InboundOrderRequest.builder()
                .orderDate(LocalDate.now())
                .orderNumber(123)
                .section(buildSectionRequest())
                .batchStock(buildStockListRequest())
                .build();
    }

    private static List<StockRequest> buildStockListRequest() {
        List<StockRequest> batch_stock = new ArrayList <>();
        batch_stock.add(StockRequest.builder()
                .batchNumber(123)
                .productId(1)
                .minimumTemperature(0.0)
                .orderNumber(123)
                .manufacturingTime(LocalDateTime.now())
                .manufacturingDate(LocalDate.now())
                .initialQuantity(10)
                .currentTemperature(10)
                .dueDate(LocalDate.now())
                .currentQuantity(10)
                .build());

        return batch_stock;
    }

    private static SectionRequest buildSectionRequest() {
        return SectionRequest.builder()
                .sectionId(1)
                .warehouseId(1)
                .build();
    }


    private static InboundOrder buildInboundOrder(){
        return InboundOrder.builder()
                .id(1L)
                .orderNumber(123)
                .orderDate(LocalDate.now())
                .agent(null)
                .batchStock(null)
                .section(null)
                .build();
    }

    private static Section buildSection() {
        return Section.builder()
                .id(1L)
                .sectionName(StorageType.FROZEN)
                .capacity(100)
                .stockList(null)
                .warehouse(null)
                .build();
    }

    private static Warehouse buildWarehouse() {

        return Warehouse.builder()
                .id(1L)
                .name("Mock")
                .sectionList(null)
                .agents(null)
                .build();
    }

    private static List<Stock> buildStockList() {
        List<Stock> batch_stock = new ArrayList <>();
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
                        .productName("product")
                        .category(StorageType.FROZEN)
                        .price(10.0)
                        .stockList(null)
                        .purchaseOrder(null)
                        .seller(null)
                        .build())
                .build());

        return batch_stock;
    }

    private static Agent buildAgent() {
        return Agent.builder()
                .id(1L)
                .inboundOrder(null)
                .warehouse(null)
                .personalData(User.builder()
                        .userType(UserType.AGENT)
                        .email("email@email.com")
                        .name("Jhon Doe")
                        .password("123456")
                        .build())
                .build();
    }
}
