package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueMarketOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketOrderCountDecreasedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrder;
import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
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
        //logger.info("LimitOrder saved into repository");
        MarketOrderDTO marketOrderDTO = issueMarketOrderEvent.getMarketOrderDTO();
        marketOrderDTORepository.save(marketOrderDTO);
    }

    @EventHandler
    public void on(MarketOrderCountDecreasedEvent marketOrderCountDecreasedEvent){
        //logger.info("LimitOrder decrease count");
        MarketOrderDTO marketOrderDTO = marketOrderDTORepository.findMarketOrderDTOById(marketOrderCountDecreasedEvent.getOrderId());
        MarketOrder marketOrder = marketOrderDTO.convertToMarketOrder();
        marketOrder.decreaseCount(marketOrderCountDecreasedEvent.getDelta());
        marketOrderDTORepository.save(marketOrder.convertToMarketOrderDTO());
    }
}
