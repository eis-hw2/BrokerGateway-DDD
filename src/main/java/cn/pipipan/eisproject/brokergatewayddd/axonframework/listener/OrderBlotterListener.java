package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.repository.OrderBlottleDTORepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderBlotterListener {
    @Autowired
    OrderBlottleDTORepository orderBlottleDTORepository;
}
