package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

public class StopOrderCancelledEvent {
    final String id;
    final String stopOrderId;

    public StopOrderCancelledEvent(String id, String stopOrderId) {
        this.id = id;
        this.stopOrderId = stopOrderId;
    }

    public String getId() {
        return id;
    }

    public String getStopOrderId() {
        return stopOrderId;
    }
}
