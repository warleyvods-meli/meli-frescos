package com.mercadolibre.dambetan01.unit.service;

import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.repository.InboundOrderRepository;
import com.mercadolibre.dambetan01.repository.SectionRepository;
import com.mercadolibre.dambetan01.repository.StockRepository;
import com.mercadolibre.dambetan01.service.impl.InboundOrderServiceImpl;
import com.mercadolibre.dambetan01.util.BuilderInboundOrderTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InboundOrderServiceImplTest {

    InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
    StockRepository stockRepository = Mockito.mock(StockRepository.class);
    SectionRepository sectionRepository = Mockito.mock(SectionRepository.class);
    InboundOrderServiceImpl service;

    @BeforeEach
    void setUp(){
        this.service = new InboundOrderServiceImpl(inboundOrderRepository,stockRepository,sectionRepository);
    }


    @Test
    public void shouldSaveOrder() {

        //given
        var order = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.save(order)).thenReturn(order);

        //when
        var receivedOrder = service.save(order);

        //then
        verify(inboundOrderRepository, times(1)).save(order);
        assertEquals(order, receivedOrder);

    }

    @Test
    void shouldSaveInboundOrderWithChild(){
        //arrange
        var orderExpected = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.save(orderExpected)).thenReturn(orderExpected);
        when(sectionRepository.findById(orderExpected.getSection().getId()))
                .thenReturn(java.util.Optional.ofNullable(orderExpected.getSection()));

        //act
        var orderReceived = service.saveWithChild(orderExpected);

        //Assert
        verify(inboundOrderRepository, times(1)).save(orderExpected);
        verify(sectionRepository, times(1)).save(orderExpected.getSection());
        verify(stockRepository, times(1)).saveAll(orderExpected.getBatchStock());
        assertEquals(orderExpected,orderReceived);
    }

    @Test
    void shouldDecreaseSectionSpace(){
        //arrange
        var order = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.save(order)).thenReturn(order);
        when(sectionRepository.findById(order.getSection().getId())).thenReturn(Optional.ofNullable(order.getSection()));

        //act
        var orderReceived = service.saveWithChild(order);

        //Assert
        assertEquals(90, orderReceived.getSection().getCapacity());
    }

    @Test
    void shouldFailDecreaseSectionWhenSectionNotFound(){
        //arrange
        var order = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.save(order)).thenReturn(order);
        assertThrows(NotFoundException.class, () -> service.saveWithChild(order));
    }

    @Test
    void shouldListOrder(){
        //arrange
        List <InboundOrder> orderList = new ArrayList <>();
        orderList.add(BuilderInboundOrderTestHelper.getInboundOrder());
        when(inboundOrderRepository.findAll()).thenReturn(orderList);

        //act
        var receivedList = service.getAll();

        //assert
        assertEquals(orderList,receivedList);
    }

    @Test
    void shouldFindOrderById(){
        //arrange
        var order = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        //act
        var receivedOrder = service.findById(1L);

        //assert
        assertEquals(order,receivedOrder);
    }

    @Test
    void shouldFailWhenOrderNotFound(){
        //arrange
        var order = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.findById(1L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void shouldFillOrderStockList(){
        //arrange
        var order = BuilderInboundOrderTestHelper.getInboundOrder();
        when(inboundOrderRepository.fillStockList(order.getOrderNumber())).thenReturn(order.getBatchStock());

        //act
        var receivedOrder = service.fillStockList(order.getOrderNumber());

        //assert
        assertEquals(order.getBatchStock(),receivedOrder);
    }


}
