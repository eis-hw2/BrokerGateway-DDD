package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class LimitOrderChangedCommand {
    @TargetAggregateIdentifier
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public LimitOrderChangedCommand(String id, LimitOrderDTO limitOrderDTO) {
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
