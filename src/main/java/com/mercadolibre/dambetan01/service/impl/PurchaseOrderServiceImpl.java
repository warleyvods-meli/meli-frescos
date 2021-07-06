package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.request.PurchaseOrderRequestDTO;
import com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.mapper.PurchaseOrderMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.PurchaseOrder;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IProductService;
import com.mercadolibre.dambetan01.service.IPurcharseOrderService;
import com.mercadolibre.dambetan01.service.IStockService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.dambetan01.dtos.response.PurchaseOrderResponseDTO.*;

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
        validatedRequest(purchaseOrderRequestDTO, productList);

        purchaseOrder.setProducts(productList);
        return getPurchaseOrderResponseDTOBuilder(purchaseOrderRequestDTO, purchaseOrder).build();
    }

    private void validatedRequest(PurchaseOrderRequestDTO purchaseOrderRequestDTO, List<Product> productList) {
        for (PurchaseOrderRequestDTO.ProductDTO product : purchaseOrderRequestDTO.getProducts()) {
            if (stockService.findByProductId(product.getId()).getCurrentQuantity() > product.getQuantity()) {
                productList.add(productService.findById(product.getId()));
            }
        }
    }

    private PurchaseOrderResponseDTOBuilder getPurchaseOrderResponseDTOBuilder(PurchaseOrderRequestDTO purchaseOrderRequestDTO,
                                                                               PurchaseOrder purchaseOrder) {
        PurchaseOrderResponseDTOBuilder builder = builder();

        var totalPriceProduct = 0.0;
        var sum = 0.0;
        for (var product : purchaseOrderRepository.save(purchaseOrder).getProducts()) {
            for (var purchaseOrderRequestDTOProduct : purchaseOrderRequestDTO.getProducts()) {
                totalPriceProduct = product.getPrice() * purchaseOrderRequestDTOProduct.getQuantity();
            }
            sum += totalPriceProduct;
        }

        builder.totalPrice(sum);
        return builder;
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }


    @Override
    public List <PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }
}
