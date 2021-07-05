package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.model.InboundOrder;

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
