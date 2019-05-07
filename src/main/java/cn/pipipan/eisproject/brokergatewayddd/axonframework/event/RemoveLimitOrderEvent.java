package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RemoveLimitOrderEvent {
    @TargetAggregateIdentifier
    String id;
}
