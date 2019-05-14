package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;

public class StopOrderToMarketOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final String stopOrderId;
    final MarketOrderDTO marketOrderDTO;

    public StopOrderToMarketOrderConvertedEvent(String id, String stopOrderId, MarketOrderDTO marketOrderDTO) {
        this.id = id;
        this.stopOrderId = stopOrderId;
        this.marketOrderDTO = marketOrderDTO;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getStopOrderId() {
        return stopOrderId;
    }

    public MarketOrderDTO getMarketOrderDTO() {
        return marketOrderDTO;
    }
}
