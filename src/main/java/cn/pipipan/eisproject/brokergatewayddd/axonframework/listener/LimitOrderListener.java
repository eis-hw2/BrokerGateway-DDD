package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueLimitOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.LimitOrderChangedEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrder;
import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;
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
        LimitOrderDTO limitOrderDTO = limitOrderEvent.getLimitOrderDTO();
        limitOrderDTO.setId(limitOrderEvent.getId());
        limitOrderDTORepository.save(limitOrderDTO);
    }

    @EventHandler
    public void on(LimitOrderChangedEvent limitOrderChangedEvent){
        logger.info("limitOrderChangedEvent on");
        LimitOrderDTO limitOrderDTO = limitOrderChangedEvent.getLimitOrderDTO();
        if (limitOrderDTO.getCount() == 0) limitOrderDTO.setStatus(LimitOrder.Status.FINISHED);
        limitOrderDTORepository.save(limitOrderDTO);
    }
}
