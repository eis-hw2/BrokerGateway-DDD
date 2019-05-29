package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketClosedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;
import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import cn.pipipan.eisproject.brokergatewayddd.domain.StopOrder;
import cn.pipipan.eisproject.brokergatewayddd.repository.LimitOrderDTORepository;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketOrderDTORepository;
import cn.pipipan.eisproject.brokergatewayddd.repository.StopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarketListener {
    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;
    @Autowired
    MarketOrderDTORepository marketOrderDTORepository;
    @Autowired
    StopOrderRepository stopOrderRepository;

    public void on(MarketClosedEvent marketClosedEvent){
        List<LimitOrderDTO> limitOrderDTOS = limitOrderDTORepository.findLimitOrderDTOSByCreationTimeEqualsAndStatusEquals(marketClosedEvent.getDate(), Status.WAITING);
        limitOrderDTOS.forEach( l -> l.setStatus(Status.OUTDATED));
        limitOrderDTORepository.saveAll(limitOrderDTOS);

        List<MarketOrderDTO> marketOrderDTOS = marketOrderDTORepository.findMarketOrderDTOSByCreationTimeEqualsAndStatusEquals(marketClosedEvent.getDate(), Status.WAITING);
        marketOrderDTOS.forEach( l -> l.setStatus(Status.OUTDATED));
        marketOrderDTORepository.saveAll(marketOrderDTOS);

        List<StopOrder> stopOrders = stopOrderRepository.findStopOrdersByCreationTimeEqualsAndStatusEquals(marketClosedEvent.getDate(), Status.WAITING);
        stopOrders.forEach( l -> l.setStatus(Status.OUTDATED));
        stopOrderRepository.saveAll(stopOrders);
    }
}
