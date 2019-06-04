package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueMarketOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketOrderCancelledEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketOrderCountDecreasedEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.StopOrderToMarketOrderConvertedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrder;
import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketOrderDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketOrderListener {
    @Autowired
    MarketOrderDTORepository marketOrderDTORepository;

    @EventHandler
    public void on(IssueMarketOrderEvent issueMarketOrderEvent){
        MarketOrderDTO marketOrderDTO = issueMarketOrderEvent.getMarketOrderDTO();
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(MarketOrderCountDecreasedEvent marketOrderCountDecreasedEvent){
        //logger.info("LimitOrder decrease count");
        marketOrderDTORepository.save(marketOrderCountDecreasedEvent.getMarketOrderDTO());
    }

    @EventHandler
    public void on(MarketOrderCancelledEvent marketOrderCancelledEvent){
        MarketOrderDTO marketOrderDTO = marketOrderDTORepository.findMarketOrderDTOById(marketOrderCancelledEvent.getMarketOrderId());
        MarketOrder marketOrder = marketOrderDTO.convertToMarketOrder();
        marketOrder.setStatus(Status.CANCELLED);
        marketOrderDTORepository.save(marketOrder.convertToMarketOrderDTO());
    }

    @EventHandler
    public void on(StopOrderToMarketOrderConvertedEvent stopOrderToMarketOrderConvertedEvent){
        MarketOrderDTO marketOrderDTO = stopOrderToMarketOrderConvertedEvent.getMarketOrderDTO();
        MarketOrder marketOrder = marketOrderDTO.convertToMarketOrder();
        marketOrder.setStatus(Status.WAITING);
        marketOrderDTORepository.save(marketOrder.convertToMarketOrderDTO());
    }
}
