package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.OrderBlotterDTO;

public class IssueOrderBlotterEvent {
    final String id;
    final OrderBlotterDTO orderBlotterDTO;

    public IssueOrderBlotterEvent(String id, OrderBlotterDTO orderBlotterDTO) {
        this.id = id;
        this.orderBlotterDTO = orderBlotterDTO;
    }

    public OrderBlotterDTO getOrderBlotterDTO() {
        return orderBlotterDTO;
    }
}
