package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;

public class LimitOrderChangedEvent implements LimitOrderEvent{
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public LimitOrderChangedEvent(String id, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.limitOrderDTO = limitOrderDTO;
    }

    public LimitOrderDTO getLimitOrderDTO() {
        return limitOrderDTO;
    }
}
