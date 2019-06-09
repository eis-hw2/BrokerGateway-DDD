package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueStopOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.StopOrderCancelledEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.StopOrderConvertedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import cn.pipipan.eisproject.brokergatewayddd.domain.StopOrder;
import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import cn.pipipan.eisproject.brokergatewayddd.repository.StopOrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StopOrderListener {
    Logger logger = LoggerFactory.getLogger(StopOrder.class);
    @Autowired
    StopOrderRepository stopOrderRepository;

    @EventHandler
    public void on(IssueStopOrderEvent issueStopOrderEvent){
        stopOrderRepository.save(issueStopOrderEvent.getStopOrder());
    }

    @EventHandler
    public void on(StopOrderConvertedEvent stopOrderConvertedEvent){
        //logger.info("in StopOrderConvertedEvent");
        String id = stopOrderConvertedEvent.getStopOrderId();
        StopOrder stopOrder = stopOrderRepository.findStopOrderById(id);
        stopOrder.setStatusSwitchTime(Util.getDate(new Date()));
        stopOrder.setStatus(Status.FINISHED);
        stopOrderRepository.save(stopOrder);
    }

    @EventHandler
    public void on(StopOrderCancelledEvent stopOrderCancelledEvent){
        String id = stopOrderCancelledEvent.getStopOrderId();
        StopOrder stopOrder = stopOrderRepository.findStopOrderById(id);
        stopOrder.setStatusSwitchTime(Util.getDate(new Date()));
        stopOrder.setStatus(Status.CANCELLED);
        stopOrderRepository.save(stopOrder);
    }
}
