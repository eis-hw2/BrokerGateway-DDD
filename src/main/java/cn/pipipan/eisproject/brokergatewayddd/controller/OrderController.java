package cn.pipipan.eisproject.brokergatewayddd.controller;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.*;
import cn.pipipan.eisproject.brokergatewayddd.domain.*;
import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import cn.pipipan.eisproject.brokergatewayddd.repository.CancelOrderRepository;
import cn.pipipan.eisproject.brokergatewayddd.repository.LimitOrderDTORepository;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketOrderDTORepository;
import cn.pipipan.eisproject.brokergatewayddd.repository.StopOrderRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class OrderController {
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    CommandGateway commandGateway;
    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;
    @Autowired
    MarketOrderDTORepository marketOrderDTORepository;
    @Autowired
    CancelOrderRepository cancelOrderRepository;
    @Autowired
    StopOrderRepository stopOrderRepository;

    @PostMapping("/limitOrders")
    public Response<LimitOrderDTO> processLimitOrder(@RequestBody LimitOrderDTO limitOrderDTO){
        completeOrder(limitOrderDTO);
        try{
            commandGateway.send(new IssueLimitOrderCommand(limitOrderDTO.getMarketDepthId(), limitOrderDTO)).get();
            LimitOrderDTO res = limitOrderDTORepository.save(limitOrderDTO);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }


    @PostMapping("/marketOrders")
    public Response<MarketOrderDTO> processMarketOrder(@RequestBody MarketOrderDTO marketOrderDTO){
        completeOrder(marketOrderDTO);
        try {
            commandGateway.send(new IssueMarketOrderCommand(marketOrderDTO.getMarketDepthId(), marketOrderDTO)).get();
            MarketOrderDTO res = marketOrderDTORepository.save(marketOrderDTO);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    @PostMapping("/cancelOrders")
    public Response<CancelOrder> processCancelOrder(@RequestBody CancelOrder cancelOrder){
        completeOrder(cancelOrder);
        try{
            commandGateway.send(new IssueCancelOrderCommand(cancelOrder.getMarketDepthId(), cancelOrder)).get();
            CancelOrder res = cancelOrderRepository.save(cancelOrder);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
    }

    @PostMapping("/stopOrders")
    public Response<StopOrder> processStopOrder(@RequestBody StopOrder stopOrder){
        completeOrder(stopOrder);
        try{
            commandGateway.send(new IssueStopOrderCommand(stopOrder.getMarketDepthId(), stopOrder)).get();
            StopOrder res = stopOrderRepository.save(stopOrder);
            return new Response<>(res, 200, "OK");
        }
        catch (Exception e){
            return new Response<>(null, 500, e.getMessage());
        }
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
        addTraderName(orderDTO);
        addStatus(orderDTO);
    }

    private void addStatus(OrderDTO orderDTO) {
        orderDTO.setStatus(Status.WAITING);
    }

    private void addTraderName(OrderDTO orderDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String traderName = authentication.getName();
        orderDTO.setTraderName(traderName);
    }
}
