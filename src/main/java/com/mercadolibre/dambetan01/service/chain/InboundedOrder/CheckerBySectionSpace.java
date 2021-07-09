package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.error.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Stock;

public class CheckerBySectionSpace extends InboundOrderChecker {

    public CheckerBySectionSpace(InboundOrderChecker nextChecker) {
        super(nextChecker);
    }

    /*
        Check if the product fits in the section and if the section has space
     */
    @Override
    public boolean verify(InboundOrder order) {
        var neededSpace = order.getBatchStock().stream().mapToInt(Stock::getCurrentQuantity).sum();
        if (order.getSection().getCapacity() < neededSpace) {
            throw new BadRequestException("there is no more space in this section");
        }
        return nextChecker.verify(order);
    }
}
