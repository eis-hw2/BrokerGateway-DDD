package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;

public class LimitOrderCountDecreasedEvent {
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public LimitOrderCountDecreasedEvent(String id, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.limitOrderDTO = limitOrderDTO;
    }

    public LimitOrderDTO getLimitOrderDTO(){
        return this.limitOrderDTO;
    }
}
