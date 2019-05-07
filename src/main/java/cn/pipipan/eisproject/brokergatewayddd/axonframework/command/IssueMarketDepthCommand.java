package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueMarketDepthCommand {
    @TargetAggregateIdentifier
    final String id;

    public IssueMarketDepthCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
