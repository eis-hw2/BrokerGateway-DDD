package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class LimitOrderDecreaseCountEvent {
    @TargetAggregateIdentifier
    final String id;
    final int count;

    public LimitOrderDecreaseCountEvent(String id, int count) {
        this.id = id;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
