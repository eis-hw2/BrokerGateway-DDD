package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;

public class IssueOrderBlotterEvent {
    final String id;
    final LimitOrderDTO buyer_order;
    final LimitOrderDTO seller_order;
    final int delta;

    public IssueOrderBlotterEvent(String id, LimitOrderDTO buyer_order, LimitOrderDTO seller_order, int delta) {
        this.id = id;
        this.buyer_order = buyer_order;
        this.seller_order = seller_order;
        this.delta = delta;
    }

    public LimitOrderDTO getBuyer_order() {
        return buyer_order;
    }

    public LimitOrderDTO getSeller_order() {
        return seller_order;
    }

    public int getDelta() {
        return delta;
    }
}
