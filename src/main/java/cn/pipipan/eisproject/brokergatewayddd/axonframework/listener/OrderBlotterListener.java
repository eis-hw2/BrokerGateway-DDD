package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueOrderBlotterEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.OrderBlotterDTO;
import cn.pipipan.eisproject.brokergatewayddd.repository.OrderBlotterDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBlotterListener {
    @Autowired
    OrderBlotterDTORepository orderBlotterDTORepository;

    @EventHandler
    public void on(IssueOrderBlotterEvent issueOrderBlotterEvent){
        orderBlotterDTORepository.save(OrderBlotterDTO.createOrderBlotter(issueOrderBlotterEvent));
    }
}
