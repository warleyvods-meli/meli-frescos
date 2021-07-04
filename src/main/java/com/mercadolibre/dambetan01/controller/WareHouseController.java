package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.request.InboundOrderRequest;
import com.mercadolibre.dambetan01.dtos.response.InboundOrderResponse;
import com.mercadolibre.dambetan01.mapper.InboundOrderMapper;
import com.mercadolibre.dambetan01.model.Agent;
import com.mercadolibre.dambetan01.model.InboundOrder;
import com.mercadolibre.dambetan01.service.IAgentService;
import com.mercadolibre.dambetan01.service.IInboundOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1")
@RestController
public class WareHouseController {

    private final IAgentService agentService;
    private final InboundOrderMapper inboundOrderMapper;
    private final IInboundOrderService inboundOrderService;

    public WareHouseController(IAgentService agentService, InboundOrderMapper inboundOrderMapper, IInboundOrderService inboundOrderService) {
        this.agentService = agentService;
        this.inboundOrderMapper = inboundOrderMapper;
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping("/fresh-products/inboundorder")
    @ResponseBody
    public ResponseEntity <InboundOrderResponse> createInboundOrder(@RequestHeader(value = "Authorization") String token,
                                                                    @RequestHeader(value = "AgentId") int agentId,
                                                                    @RequestBody InboundOrderRequest request) {
        Agent agent = agentService.findById((long) agentId);
        var orderToCheck = inboundOrderMapper.requestToEntity(request);
        orderToCheck.setAgent(agent);
        InboundOrder validOrder = agent.createInboundOrder(orderToCheck);
        InboundOrderResponse response = inboundOrderMapper.EntityToResponse(inboundOrderService.saveWithChild(validOrder));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
