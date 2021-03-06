package com.mercadolibre.dambetan01.service.chain.InboundedOrder;

import com.mercadolibre.dambetan01.exceptions.error.BadRequestException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.model.Section;
import com.mercadolibre.dambetan01.model.Stock;

public class CheckerByStockTemperature extends InboundOrderChecker {

    public CheckerByStockTemperature(InboundOrderChecker nextChecker) {
        super(nextChecker);
    }

    /*
        Check if the stock temperature fits in the section.
     */
    @Override
    public boolean verify(InboundOrder order) {
        order.getBatchStock().forEach(stock -> checkTemperatureRange(order.getSection(), stock));
        return nextChecker.verify(order);
    }

    private void checkTemperatureRange(Section section, Stock stock) {
        var minRangeValue = section.getSectionName().getPreviousStorage().getTemperature();
        var maxRangeValue = section.getSectionName().getNextStorage().getTemperature();
        var temperatureToCheck = stock.getCurrentTemperature();
        if (temperatureToCheck < minRangeValue || temperatureToCheck > maxRangeValue) {
            throw new BadRequestException("stock " + stock.getBatchNumber() + " temperature does not match witch section temperature");
        }
    }
}
