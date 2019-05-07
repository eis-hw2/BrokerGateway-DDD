package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketDepthDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class MarketDepthFixedEvent {
    @TargetAggregateIdentifier
    final String id;
    final MarketDepthDTO marketDepthDTO;

    public MarketDepthFixedEvent(String id, MarketDepthDTO marketDepthDTO) {
        this.id = id;
        this.marketDepthDTO = marketDepthDTO;
    }

    public String getId() {
        return id;
    }

    public MarketDepthDTO getMarketDepthDTO() {
        return marketDepthDTO;
    }
}
