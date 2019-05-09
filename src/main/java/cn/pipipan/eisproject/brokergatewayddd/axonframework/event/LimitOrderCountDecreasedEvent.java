package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

public class LimitOrderCountDecreasedEvent {
    final String id;
    final String orderId;
    final int count;

    public LimitOrderCountDecreasedEvent(String id, String orderId, int count) {
        this.id = id;
        this.orderId = orderId;
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getCount() {
        return count;
    }
}
