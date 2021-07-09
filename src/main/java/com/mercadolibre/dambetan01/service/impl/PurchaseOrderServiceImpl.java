package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.ProductResponseDTO;
import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.mapper.PurchaseOrderMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IProductService;
import com.mercadolibre.dambetan01.service.IPurcharseOrderService;
import com.mercadolibre.dambetan01.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.dambetan01.mapper.ProductMapper.INSTANCE;
import static java.time.temporal.ChronoUnit.*;
import static java.util.stream.IntStream.*;

@Slf4j
@Service
public class PurchaseOrderServiceImpl implements IPurcharseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final IProductService productService;
    private final IStockService stockService;
    private final PurchaseOrderMapper purchaseOrderMapper;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, IProductService productService,
                                    IStockService stockService, PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productService = productService;
        this.stockService = stockService;
        this.purchaseOrderMapper = purchaseOrderMapper;
    }

    @Override
    public PurchaseOrderResponseDTO savePurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        var purchaseOrder = purchaseOrderMapper.toPurchaseOrder(purchaseOrderRequestDTO);
        purchaseOrder.setDataOrder(LocalDate.now());

        List<Product> productList = new ArrayList<>();
        validateProductRequest(purchaseOrderRequestDTO, productList);
        purchaseOrder.setProducts(productList);

        var purchaseOrderSaved = purchaseOrderRepository.save(purchaseOrder);

        List<Product> products = purchaseOrderSaved.getProducts();
        double totalPriceOrder = range(0, products.size())
                .mapToDouble(i -> products.get(i).getPrice() * purchaseOrderRequestDTO.getProducts().get(i).getQuantity()).sum();

        return PurchaseOrderResponseDTO.builder().totalPrice(totalPriceOrder).build();
    }

    private void validateProductRequest(PurchaseOrderRequestDTO purchaseOrderRequestDTO, List<Product> productList) {
        for (PurchaseOrderRequestDTO.ProductDTO product : purchaseOrderRequestDTO.getProducts()) {
            var stockFound = stockService.findByProductId(product.getId());
            var productFound = productService.findById(product.getId());

            if (stockFound.getCurrentQuantity() >= product.getQuantity() && DAYS.between(stockFound.getManufacturingDate(), LocalDate.now()) <= 14) {
                productList.add(productFound);
            } else {
                log.info("Product " + productFound.getProductName() + " expiration date is older than 3 weeks");
            }
        }
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Purchase order not found!"));
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public List<ProductResponseDTO> listAllProductsInOrder(Long idOrder) {
        var purchaseOrder = findById(idOrder);
        return INSTANCE.productListToDtoList(purchaseOrder.getProducts());
    }

    @Override
    public PurchaseOrderResponseDTO editOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        findById(purchaseOrderRequestDTO.getId());
        return savePurchaseOrder(purchaseOrderRequestDTO);
    }

}
