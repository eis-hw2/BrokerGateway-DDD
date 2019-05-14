package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;
import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;

public class StopOrderToLimitOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final LimitOrderDTO limitOrderDTO;

    public StopOrderToLimitOrderConvertedEvent(String id, LimitOrderDTO limitOrderDTO) {
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
