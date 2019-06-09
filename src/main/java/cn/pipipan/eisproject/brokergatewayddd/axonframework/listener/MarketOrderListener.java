package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueMarketOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketOrderCancelledEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketOrderCountDecreasedEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.StopOrderToMarketOrderConvertedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketOrderDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        MarketOrderDTO marketOrderDTO = marketOrderCountDecreasedEvent.getMarketOrderDTO();
        marketOrderDTO.setStatusSwitchTime(Util.getDate(new Date()));
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(MarketOrderCancelledEvent marketOrderCancelledEvent){
        MarketOrderDTO marketOrderDTO = marketOrderDTORepository.findMarketOrderDTOById(marketOrderCancelledEvent.getMarketOrderId());
        marketOrderDTO.setStatus(Status.CANCELLED);
        marketOrderDTO.setStatusSwitchTime(Util.getDate(new Date()));
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(StopOrderToMarketOrderConvertedEvent stopOrderToMarketOrderConvertedEvent){
        MarketOrderDTO marketOrderDTO = stopOrderToMarketOrderConvertedEvent.getMarketOrderDTO();
        marketOrderDTORepository.save(marketOrderDTO);
    }
}
