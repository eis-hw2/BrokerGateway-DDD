package cn.pipipan.eisproject.brokergatewayddd.controller;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueMarketOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueCancelOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueStopOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.domain.*;
import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import cn.pipipan.eisproject.brokergatewayddd.repository.LimitOrderDTORepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    CommandGateway commandGateway;

    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;

    @PostMapping("/LimitOrder")
    public Response<String> processLimitOrder(@RequestBody LimitOrderDTO limitOrderDTO){
        completeOrder(limitOrderDTO);
        commandGateway.send(new IssueLimitOrderCommand(limitOrderDTO.getMarketDepthId(), limitOrderDTO));
        return new Response<>(limitOrderDTO.getId(), 200, "OK");
    }


    @PostMapping("/MarketOrder")
    public Response<String> processMarketOrder(@RequestBody MarketOrderDTO marketOrderDTO){
        completeOrder(marketOrderDTO);
        commandGateway.send(new IssueMarketOrderCommand(marketOrderDTO.getMarketDepthId(), marketOrderDTO));
        return new Response<>(marketOrderDTO.getId(), 200, "OK");
    }

    @PostMapping("/CancelOrder")
    public Response<String> processCancelOrder(@RequestBody CancelOrder cancelOrder){
        completeOrder(cancelOrder);
        commandGateway.send(new IssueCancelOrderCommand(cancelOrder.getMarketDepthId(), cancelOrder));
        return new Response<>(cancelOrder.getId(), 200, "OK");
    }

    @PostMapping("/StopOrder")
    public Response<String> processStopOrder(@RequestBody StopOrder stopOrder){
        completeOrder(stopOrder);
        commandGateway.send(new IssueStopOrderCommand(stopOrder.getMarketDepthId(), stopOrder));
        return new Response<>(stopOrder.getId(), 200, "OK");
    }

    private void addOrderId(OrderDTO orderDTO){
        String id = UUID.randomUUID().toString();
        orderDTO.setId(id);
    }

    private void addCreationTime(OrderDTO orderDTO){
        String creationTime = Util.getDate(new Date());
        orderDTO.setCreationTime(creationTime);
    }

    private void completeOrder(OrderDTO orderDTO) {
        addOrderId(orderDTO);
        addCreationTime(orderDTO);

    }
}
