package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class CheckerBySection extends InboundOrderChecker{

    public CheckerBySection(InboundOrderChecker nextChecker) {
        super(nextChecker);
    }

    /*
        Check if the product fits in the section and if the section has space
     */
    @Override
    public boolean verify(InboundOrder order, Warehouse warehouse) {
        List <Product> products = new ArrayList <>();
        order.getBatchStock().forEach(stock -> products.add(stock.getProduct()));
        var notMatchProducts = products
                .stream()
                .filter(product -> !product.getCategory().getTemperature().equals(order.getSection().getSectionName().getTemperature()));
          if (notMatchProducts.count() > 0) {
              throw new BadRequestException("there are products incompatible with the section");
          }
          if(order.getSection().getCapacity() < products.size()){
              throw new BadRequestException("there is no more space in this section");
          }
        return true;
    }
}
