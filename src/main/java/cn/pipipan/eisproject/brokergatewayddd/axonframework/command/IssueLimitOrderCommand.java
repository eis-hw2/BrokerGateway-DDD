package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueLimitOrderCommand {
    @TargetAggregateIdentifier
    private String id;
    private LimitOrderDTO limitOrderDTO;

    public IssueLimitOrderCommand(String id, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.limitOrderDTO = limitOrderDTO;
    }

    public LimitOrderDTO getLimitOrderDTO() {
        return limitOrderDTO;
    }

    public void setLimitOrderDTO(LimitOrderDTO limitOrderDTO) {
        this.limitOrderDTO = limitOrderDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
