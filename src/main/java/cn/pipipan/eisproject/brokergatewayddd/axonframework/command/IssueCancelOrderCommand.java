package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import cn.pipipan.eisproject.brokergatewayddd.domain.CancelOrder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueCancelOrderCommand {
    @TargetAggregateIdentifier
    final String id;
    final CancelOrder cancelOrder;

    public String getId() {
        return id;
    }

    public CancelOrder getCancelOrder() {
        return cancelOrder;
    }

    public IssueCancelOrderCommand(String id, CancelOrder cancelOrder) {
        this.id = id;
        this.cancelOrder = cancelOrder;
    }
}
