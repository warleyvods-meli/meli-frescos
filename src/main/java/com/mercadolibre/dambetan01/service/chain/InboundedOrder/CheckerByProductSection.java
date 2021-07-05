package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.model.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckerByProductSection extends InboundOrderChecker{

    public CheckerByProductSection(InboundOrderChecker nextChecker) {
        super(nextChecker);
    }

    /*
        Check if the product fits in the section and if the section has space
     */
    @Override
    public boolean verify(InboundOrder order) {
        List <Product> products = new ArrayList <>();
        order.getBatchStock().forEach(stock -> products.add(stock.getProduct()));
        var notMatchProducts = products
                .stream()
                .filter(product -> !product.getCategory().getTemperature().equals(order.getSection().getSectionName().getTemperature())).count();
        if (notMatchProducts > 0L) {
            throw new BadRequestException("there are products incompatible with the section");
        }
        return nextChecker.verify(order);
    }
}
//TODO rever a regra de temperatura do produto, pois o assert está no stock
