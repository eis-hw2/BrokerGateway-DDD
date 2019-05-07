package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class InsertLimitOrderEvent {
    @TargetAggregateIdentifier
    final String id;
    final LimitOrder limitOrder;

    public InsertLimitOrderEvent(String id, LimitOrder limitOrder) {
        this.id = id;
        this.limitOrder = limitOrder;
    }

    public String getId() {
        return id;
    }

    public LimitOrder getLimitOrder() {
        return limitOrder;
    }
}
