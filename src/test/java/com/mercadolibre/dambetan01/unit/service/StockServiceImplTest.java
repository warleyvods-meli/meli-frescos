package com.mercadolibre.dambetan01.unit.service;

import com.mercadolibre.dambetan01.model.Stock;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.service.impl.StockServiceImpl;
import com.mercadolibre.dambetan01.util.StockTestHelper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceImplTest {

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    @DisplayName("Deve retornar uma lista de estoques com a data de validade vencida, de acordo com quantidade de dias informados")
    public void shouldGetListStockFromSectionByDueDateTest(){

        //arrange
        List<Stock> listActual = StockTestHelper.buildStockListActual();
        List<Stock> listFuture = StockTestHelper.buildStockListFuture();
        ArrayList<Stock> newList = new ArrayList<>();
        newList.addAll(listActual);
        newList.addAll(listFuture);

        Iterable<Long> listLong = StockTestHelper.getListIterable();
        when(sectionRepository.findStockList(1l)).thenReturn((List<Long>) listLong);
        when(stockRepository.findAllById(Mockito.anyIterable())).thenReturn(newList);

        //act
        List<Stock> listed = stockService.findStockFromSectionDueDate(1l, 2l);

        //assert
        assertNotSame(listActual, listed);
        assertEquals(2, listed.size());

    }

    @Test
    @DisplayName("Deve retornar uma lista de estoques com a data de validade vencida, de acordo com quantidade de dias informados e categoria")
    public void shouldGetListStockFromSectionByDueDateAndCategoryTest(){

        //arrange
        List<Stock> listActual = StockTestHelper.buildStockListActual();
        List<Stock> listFuture = StockTestHelper.buildStockListFuture();
        ArrayList<Stock> newList = new ArrayList<>();
        newList.addAll(listActual);
        newList.addAll(listFuture);

        Iterable<Long> listLong = StockTestHelper.getListIterable();
        when(sectionRepository.findStockList(1l)).thenReturn((List<Long>) listLong);
        when(stockRepository.findAllById(Mockito.anyIterable())).thenReturn(newList);

        //act
        List<Stock> listed = stockService.findStockFromSectionDueDateCategory(1l, 2l, "FROZEN");

        //assert
        assertNotSame(listActual, listed);
        assertEquals(1, listed.size());

    }

}
