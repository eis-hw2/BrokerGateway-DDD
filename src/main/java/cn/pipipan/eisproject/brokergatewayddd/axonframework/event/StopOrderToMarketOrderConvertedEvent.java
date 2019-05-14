package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;

public class StopOrderToMarketOrderConvertedEvent implements StopOrderConvertedEvent{
    final String id;
    final MarketOrderDTO marketOrderDTO;

    public StopOrderToMarketOrderConvertedEvent(String id, MarketOrderDTO marketOrderDTO) {
        this.id = id;
        this.marketOrderDTO = marketOrderDTO;
    }

    public String getId() {
        return id;
    }

    public MarketOrderDTO getMarketOrderDTO() {
        return marketOrderDTO;
    }
}
