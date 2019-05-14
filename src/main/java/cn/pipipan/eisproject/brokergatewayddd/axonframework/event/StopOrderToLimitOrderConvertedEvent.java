package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;
import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrderDTO;

public class StopOrderToLimitOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final String stopOrderId;
    final LimitOrderDTO limitOrderDTO;

    public StopOrderToLimitOrderConvertedEvent(String id, String stopOrderId, LimitOrderDTO limitOrderDTO) {
        this.id = id;
        this.stopOrderId = stopOrderId;
        this.limitOrderDTO = limitOrderDTO;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getStopOrderId() {
        return stopOrderId;
    }

    public LimitOrderDTO getLimitOrderDTO() {
        return limitOrderDTO;
    }
}
