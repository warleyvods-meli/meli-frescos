package com.mercadolibre.dambetan01.util;

import com.mercadolibre.dambetan01.dtos.response.ProductLocationResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.SectionResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockAndSectionResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockResponseDTO;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.model.Warehouse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BuilderProductServiceTest {

    Product product;
    Section section;
    Stock stock;
    Warehouse warehouse;

    public void createObjects(){

        Product product = Product.builder().build();
        product.setId(1L);

        Stock stock = Stock.builder().build();
        stock.setId(1L);
        stock.setBatchNumber(123);
        stock.setCurrentQuantity(2);
        stock.setDueDate(LocalDate.now());

        Section section = Section.builder().build();
        section.setId(1L);

        Warehouse warehouse = Warehouse.builder().build();
        warehouse.setId(1L);

        List<Stock> stockList = new ArrayList<>();
        List<Section> sectionList = new ArrayList<>();

        stockList.add(stock);
        sectionList.add(section);
        stock.setSection(section);
        product.setStockList(stockList);
        warehouse.setSectionList(sectionList);
        section.setWarehouse(warehouse);

        this.product = product;
        this.section = section;
        this.stock = stock;
        this.warehouse = warehouse;
    }

    public ProductLocationResponseDTO getAllProductLocationResponse() {

        StockResponseDTO stockExpected = StockResponseDTO.builder().
                batchNumber(this.stock.getBatchNumber())
                .currentQuantity(this.stock.getCurrentQuantity())
                .dueDate(this.stock.getDueDate()).build();

        SectionResponseDTO sectionExpected = SectionResponseDTO.builder()
                .sectionId(this.section.getId())
                .warehouseId(this.warehouse.getId())
                .build();

        StockAndSectionResponseDTO  sectionAndStockExpected = StockAndSectionResponseDTO
                .builder()
                .section(sectionExpected)
                .stock(stockExpected)
                .build();

        List<StockAndSectionResponseDTO> sectionAndStockExpectedList = new ArrayList<>();
        sectionAndStockExpectedList.add(sectionAndStockExpected);
        return ProductLocationResponseDTO.builder()
                    .productId(this.product.getId())
                    .batchStock(sectionAndStockExpectedList)
                    .build();
        }

}
