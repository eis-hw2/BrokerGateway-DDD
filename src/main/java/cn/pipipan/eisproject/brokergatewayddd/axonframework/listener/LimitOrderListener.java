package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueLimitOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.LimitOrderCancelledEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.LimitOrderCountDecreasedEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.StopOrderToLimitOrderConvertedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import cn.pipipan.eisproject.brokergatewayddd.repository.LimitOrderDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LimitOrderListener {
    Logger logger = LoggerFactory.getLogger(LimitOrderListener.class);
    @Autowired
    LimitOrderDTORepository limitOrderDTORepository;

    @EventHandler
    public void on(IssueLimitOrderEvent limitOrderEvent){
        //logger.info("LimitOrder saved into repository");
        LimitOrderDTO limitOrderDTO = limitOrderEvent.getLimitOrderDTO();
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on(LimitOrderCountDecreasedEvent limitOrderCountDecreasedEvent){
        //logger.info("LimitOrder decrease count");
        LimitOrderDTO limitOrderDTO = limitOrderCountDecreasedEvent.getLimitOrderDTO();
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on(LimitOrderCancelledEvent limitOrderCancelledEvent){
        //logger.info("LimitOrder decrease count");
        LimitOrderDTO limitOrderDTO = limitOrderDTORepository.findLimitOrderDTOById(limitOrderCancelledEvent.getLimitOrderId());
        limitOrderDTO.setStatus(Status.CANCELLED);
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on (StopOrderToLimitOrderConvertedEvent stopOrderToLimitOrderConvertedEvent){
        LimitOrderDTO limitOrderDTO = stopOrderToLimitOrderConvertedEvent.getLimitOrderDTO();
        limitOrderDTO.setStatus(Status.WAITING);
        limitOrderDTORepository.save(limitOrderDTO);
    }
}
