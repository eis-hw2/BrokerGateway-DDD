package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

public class MarketOrderCancelledEvent {
    final String id;
    final String marketOrderId;
    public MarketOrderCancelledEvent(String id, String marketOrderId) {
        this.id = id;
        this.marketOrderId = marketOrderId;
    }

    public String getId() {
        return id;
    }

    public String getMarketOrderId() {
        return marketOrderId;
    }
}
