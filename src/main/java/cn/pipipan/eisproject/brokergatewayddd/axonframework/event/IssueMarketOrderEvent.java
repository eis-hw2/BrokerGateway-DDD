package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueMarketOrderEvent {
    @TargetAggregateIdentifier
    String marketDepthId;
    MarketOrderDTO marketOrderDTO;
    public IssueMarketOrderEvent(String marketDepthId, MarketOrderDTO marketOrderDTO) {
        this.marketDepthId = marketDepthId;
        this.marketOrderDTO = marketOrderDTO;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public MarketOrderDTO getMarketOrderDTO() {
        return marketOrderDTO;
    }

    public void setMarketOrderDTO(MarketOrderDTO marketOrderDTO) {
        this.marketOrderDTO = marketOrderDTO;
    }
}
