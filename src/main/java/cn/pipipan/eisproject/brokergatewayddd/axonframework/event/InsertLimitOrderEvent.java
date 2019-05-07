package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class InsertLimitOrderEvent {
    @TargetAggregateIdentifier
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public InsertLimitOrderEvent(String id, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.limitOrderDTO = limitOrderDTO;
    }

    public String getId() {
        return id;
    }

    public LimitOrderDTO getLimitOrderDTO() {
        return limitOrderDTO;
    }
}
