package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Warehouse;

public class CheckerFinalyze extends InboundOrderChecker{

    public CheckerFinalyze() {
        super(null);
    }

    /*
        check if the agent belongs to warehouse
     */
    @Override
    public boolean verify(InboundOrder order) {
        return true;
    }
}
