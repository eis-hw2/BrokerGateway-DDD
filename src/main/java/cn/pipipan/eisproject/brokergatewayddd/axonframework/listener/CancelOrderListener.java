package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.CancelOrderFinishedEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueCancelOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.CancelOrder;
import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import cn.pipipan.eisproject.brokergatewayddd.repository.CancelOrderRepository;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CancelOrderListener {
    @Autowired
    CancelOrderRepository cancelOrderRepository;

    @EventSourcingHandler
    public void on(IssueCancelOrderEvent issueCancelOrderEvent){
        cancelOrderRepository.save(issueCancelOrderEvent.getCancelOrder());
    }

    @EventSourcingHandler
    public void on(CancelOrderFinishedEvent cancelOrderFinishedEvent){
        CancelOrder cancelOrder = cancelOrderRepository.findCancelOrderById(cancelOrderFinishedEvent.getCancelOrderId());
        cancelOrder.setStatus(cancelOrderFinishedEvent.getTargetStatus());
        cancelOrder.setStatusSwitchTime(Util.getDate(new Date()));
        cancelOrderRepository.save(cancelOrder);
    }
}
