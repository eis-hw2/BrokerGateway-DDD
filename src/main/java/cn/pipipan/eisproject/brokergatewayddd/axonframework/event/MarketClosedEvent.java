package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketClosedEvent {
    public MarketClosedEvent(String id, String date) {
        this.id = id;
        this.date = date;
    }

    @TargetAggregateIdentifier
    String id;
    String date;

    public String getDate() {
        return date;
    }
}
