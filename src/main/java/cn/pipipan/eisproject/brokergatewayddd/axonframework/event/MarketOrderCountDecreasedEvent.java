package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

public class MarketOrderCountDecreasedEvent {
    final String id;
    final String orderId;
    final int delta;
    public MarketOrderCountDecreasedEvent(String id, String orderId, int delta) {
        this.id = id;
        this.orderId = orderId;
        this.delta = delta;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getDelta() {
        return delta;
    }
}
