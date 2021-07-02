package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.request.InboundOrderRequest;
import com.mercadolibre.dambetan01.dtos.request.TestRequest;
import com.mercadolibre.dambetan01.mappers.InboundOrderMapper;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.service.impl.InboundOrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class InboundOrderController {

    private InboundOrderServiceImpl inboundOrderServiceImpl;
    private InboundOrderMapper mapper;

    public InboundOrderController(InboundOrderServiceImpl inboundOrderServiceImpl) {
        this.inboundOrderServiceImpl = inboundOrderServiceImpl;
    }

    @PostMapping("/fresh-products/inboundorder")
    public ResponseEntity<InboundOrder> inboundOrderRequest(@RequestBody InboundOrderRequest request){
        InboundOrder inboundOrder = inboundOrderServiceImpl.createInboundOrder(mapper.RequestToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(inboundOrder);
    }

    @PostMapping("/teste")
    public String postando(@RequestBody TestRequest test){
        return test.getName();
    }


}
