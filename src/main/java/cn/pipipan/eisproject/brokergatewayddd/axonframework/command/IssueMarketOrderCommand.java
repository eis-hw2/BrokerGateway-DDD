package cn.pipipan.eisproject.brokergatewayddd.axonframework.command;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueMarketOrderCommand implements IssueOrderCommand{
    @TargetAggregateIdentifier
    String marketDepthId;
    MarketOrderDTO marketOrderDTO;
    public IssueMarketOrderCommand(String marketDepthId, MarketOrderDTO marketOrderDTO) {
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
