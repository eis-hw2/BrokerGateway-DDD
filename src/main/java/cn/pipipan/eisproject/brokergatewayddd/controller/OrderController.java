package cn.pipipan.eisproject.brokergatewayddd.controller;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueMarketOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueCancelOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueStopOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.domain.*;
import cn.pipipan.eisproject.brokergatewayddd.repository.LimitOrderDTORepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    CommandGateway commandGateway;

    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;

    @PostMapping("/LimitOrder")
    public void processLimitOrder(@RequestBody LimitOrderDTO limitOrderDTO){
        addOrderId(limitOrderDTO);
        commandGateway.send(new IssueLimitOrderCommand(limitOrderDTO.getMarketDepthId(), limitOrderDTO));
    }

    @PostMapping("/MarketOrder")
    public void processMarketOrder(@RequestBody MarketOrderDTO marketOrderDTO){
        addOrderId(marketOrderDTO);
        commandGateway.send(new IssueMarketOrderCommand(marketOrderDTO.getMarketDepthId(), marketOrderDTO));
    }

    @PostMapping("/CancelOrder")
    public void processCancelOrder(@RequestBody CancelOrder cancelOrder){
        addOrderId(cancelOrder);
        commandGateway.send(new IssueCancelOrderCommand(cancelOrder.getMarketDepthId(), cancelOrder));
    }

    @PostMapping("/StopOrder")
    public void processStopOrder(@RequestBody StopOrder stopOrder){
        addOrderId(stopOrder);
        commandGateway.send(new IssueStopOrderCommand(stopOrder.getMarketDepthId(), stopOrder));
    }

    private void addOrderId(OrderDTO orderDTO){
        String id = UUID.randomUUID().toString();
        orderDTO.setId(id);
    }
}
