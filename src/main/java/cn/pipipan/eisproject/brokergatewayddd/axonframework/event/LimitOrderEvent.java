package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;

public interface LimitOrderEvent {
    public LimitOrderDTO getLimitOrderDTO();
}
