package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketDepthChangedCommand {
    @TargetAggregateIdentifier
    final String id;

    public MarketDepthChangedCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
