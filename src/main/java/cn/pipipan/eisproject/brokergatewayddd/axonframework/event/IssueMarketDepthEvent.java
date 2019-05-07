package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueMarketDepthEvent {
    @TargetAggregateIdentifier
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IssueMarketDepthEvent(String id) {
        this.id = id;
    }
}
