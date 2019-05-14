package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueStopOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.StopOrderConvertedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import cn.pipipan.eisproject.brokergatewayddd.domain.StopOrder;
import cn.pipipan.eisproject.brokergatewayddd.repository.StopOrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StopOrderListener {
    @Autowired
    StopOrderRepository stopOrderRepository;

    @EventHandler
    public void on(IssueStopOrderEvent issueStopOrderEvent){
        stopOrderRepository.save(issueStopOrderEvent.getStopOrder());
    }

    @EventHandler
    public void on(StopOrderConvertedEvent stopOrderConvertedEvent){
        String id = stopOrderConvertedEvent.getId();
        StopOrder stopOrder = stopOrderRepository.findStopOrderById(id);
        stopOrder.setStatus(Status.FINISHED);
        stopOrderRepository.save(stopOrder);
    }
}
