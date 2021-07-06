package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.error.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;

public class CheckerByWarehouse extends InboundOrderChecker{

    public CheckerByWarehouse(InboundOrderChecker nextChecker) {
        super(nextChecker);
    }

    /*
        check if  section belongs to warehouse
     */
    @Override
    public boolean verify(InboundOrder order) {
        var result = order.getSection().getWarehouse().getSectionList()
                .stream()
                .anyMatch(section -> section.getSectionName().getTemperature().equals(order.getSection().getSectionName().getTemperature()));
        if (result) {
            return nextChecker.verify(order);
        }
        throw new BadRequestException("Section " + order.getSection().getSectionName() +
                "does not belong to " + order.getSection().getWarehouse().getName() );
    }
}
