package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketDepthFixedEvent;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketDepthDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketDepthListener {
    @Autowired
    MarketDepthDTORepository marketDepthDTORepository;

    @EventHandler
    public void on(MarketDepthFixedEvent marketDepthFixedEvent){
        marketDepthDTORepository.save(marketDepthFixedEvent.getMarketDepthDTO());
    }
}
