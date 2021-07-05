package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Warehouse;

public abstract class InboundOrderChecker {

    protected InboundOrderChecker nextChecker;

    public InboundOrderChecker(InboundOrderChecker nextChecker) {
        this.nextChecker = nextChecker;
    }

    public abstract boolean verify(InboundOrder order);
}
