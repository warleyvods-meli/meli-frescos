package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.BadRequestException;
import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Warehouse;

public class CheckerByAgent extends InboundOrderChecker{

    public CheckerByAgent(InboundOrderChecker nextChecker) {
        super(nextChecker);
    }

    /*
        check if the agent belongs to warehouse
     */
    @Override
    public boolean verify(InboundOrder order) {
          var result = order.getSection().getWarehouse().getAgents()
                  .stream()
                  .anyMatch(agent -> agent.getPersonalData().getEmail().equals(order.getAgent().getPersonalData().getEmail()));
          if (result) {
              return nextChecker.verify(order);
          }
          throw new BadRequestException("Agent " + order.getAgent().getPersonalData().getName() +
                  "does not belong to " + order.getSection().getWarehouse().getName() );
    }
}
