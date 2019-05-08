package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.Order;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueOrderBlotterEvent {
    @TargetAggregateIdentifier
    final String id;
    final Order order1;
    final Order order2;
    final int count;

    public IssueOrderBlotterEvent(String id, Order order1, Order order2, int count) {
        this.id = id;
        this.order1 = order1;
        this.order2 = order2;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getId() {
        return id;
    }

    public Order getOrder1() {
        return order1;
    }

    public Order getOrder2() {
        return order2;
    }
}
