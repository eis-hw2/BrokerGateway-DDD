package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.OrderDTO;

public class IssueOrderBlotterEvent {
    final String id;
    final OrderDTO buyer_order;
    final OrderDTO seller_order;
    final int delta;

    public IssueOrderBlotterEvent(String id, OrderDTO buyer_order, OrderDTO seller_order, int delta) {
        this.id = id;
        this.buyer_order = buyer_order;
        this.seller_order = seller_order;
        this.delta = delta;
    }

    public OrderDTO getBuyer_order() {
        return buyer_order;
    }

    public OrderDTO getSeller_order() {
        return seller_order;
    }

    public int getDelta() {
        return delta;
    }
}
