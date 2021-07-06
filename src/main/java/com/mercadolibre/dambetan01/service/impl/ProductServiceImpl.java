package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.ProductLocationResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.SectionResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockAndSectionResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.StockResponseDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.mapper.SectionMapper;
import com.mercadolibre.dambetan01.mapper.StockMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final SectionRepository sectionRepository;
    private final StockMapper stockMapper;
    private final SectionMapper sectionMapper;



    public ProductServiceImpl(ProductRepository productRepository, StockRepository stockRepository, SectionRepository sectionRepository, StockMapper stockMapper, SectionMapper sectionMapper) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.sectionRepository = sectionRepository;
        this.stockMapper = stockMapper;
        this.sectionMapper = sectionMapper;
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product not found."));
    }

    @Override
    public List <Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductLocationResponseDTO getProductLocation(Long productId, Long warehouseId){
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("not found"));
        List<Stock> stockList = stockRepository.findAllById(this.getStocksIdList(product.getStockList()));
        return ProductLocationResponseDTO.builder().productId(product.getId()).batchStock(this.getStockLocations(stockList, warehouseId)).build();

    }

    private Iterable<Long> getStocksIdList(List<Stock> stockList){
        List<Long> idList = new ArrayList<>();
        for (Stock s : stockList){
            idList.add(s.getId());
        }
        return idList;
    }

    private Iterable<Long> getSectionsIdList(List<Stock> stockList){
        List<Long> idList = new ArrayList<>();
        for(Stock s : stockList){
            idList.add(s.getSection().getId());
        }
        return idList;
    }

    private List<StockAndSectionResponseDTO> getStockLocations(List<Stock> stockList, Long warehouseId){
        List<StockAndSectionResponseDTO> stockAndSectionResponseDTOList = new ArrayList<>();
        for(Stock s : stockList){
            StockResponseDTO stockResponse = stockMapper.stockEntityToResponseDTO(s);
            Section section = sectionRepository.findById(s.getSection().getId()).orElseThrow(() -> new NotFoundException("Section not found."));
            if(section.getWarehouse().getId().equals(warehouseId) || warehouseId.equals(0L)) {
                SectionResponseDTO sectionResponse = sectionMapper.sectionEntityToResponseDTO(section);
                StockAndSectionResponseDTO response =
                        StockAndSectionResponseDTO.builder()
                                .section(sectionResponse)
                                .stock(stockResponse)
                                .build();
                stockAndSectionResponseDTOList.add(response);
            }
        }
        return stockAndSectionResponseDTOList;
    }






    /*private Section getProductSection(Product productId){

    }

    private Stock getProductStock(){

    }*/

}
