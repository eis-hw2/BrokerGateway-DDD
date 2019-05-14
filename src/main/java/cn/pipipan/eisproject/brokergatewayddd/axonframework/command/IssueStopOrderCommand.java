package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import cn.pipipan.eisproject.brokergatewayddd.domain.StopOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueStopOrderCommand {
    @TargetAggregateIdentifier
    final String marketDepthId;
    final StopOrder stopOrder;

    public IssueStopOrderCommand(String marketDepthId, StopOrder stopOrder) {
        this.marketDepthId = marketDepthId;
        this.stopOrder = stopOrder;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public StopOrder getStopOrder() {
        return stopOrder;
    }
}
