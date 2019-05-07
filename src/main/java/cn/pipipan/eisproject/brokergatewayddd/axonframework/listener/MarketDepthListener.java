package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.MarketDepthFixedCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketDepthFixedEvent;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketDepthDTORepository;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MarketDepthListener {
    @Autowired
    MarketDepthDTORepository marketDepthDTORepository;

    @CommandHandler
    public void handle(MarketDepthFixedCommand marketDepthFixedCommand){
        marketDepthDTORepository.save(marketDepthFixedCommand.getMarketDepthDTO());
    }

    @EventListener
    public void on(MarketDepthFixedEvent marketDepthFixedEvent){
        marketDepthDTORepository.save(marketDepthFixedEvent.getMarketDepthDTO());
    }
}
