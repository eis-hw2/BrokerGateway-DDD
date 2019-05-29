package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueMarketQuotationEvent;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketQuotationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketQuotationListener {
    @Autowired
    MarketQuotationRepository marketQuotationRepository;

    @EventHandler
    public void on(IssueMarketQuotationEvent issueMarketQuotationEvent){
        marketQuotationRepository.save(issueMarketQuotationEvent.getMarketQuotation());
    }
}
