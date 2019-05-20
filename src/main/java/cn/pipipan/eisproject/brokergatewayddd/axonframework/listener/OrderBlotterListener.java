package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueOrderBlotterEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.OrderBlotterDTO;
import cn.pipipan.eisproject.brokergatewayddd.repository.OrderBlottleDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderBlotterListener {
    @Autowired
    OrderBlottleDTORepository orderBlottleDTORepository;

    @EventHandler
    public void on(IssueOrderBlotterEvent issueOrderBlotterEvent){
        orderBlottleDTORepository.save(OrderBlotterDTO.createOrderBlotter(issueOrderBlotterEvent));
    }
}
