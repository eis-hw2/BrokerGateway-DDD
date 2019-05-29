package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketQuotation;

public class IssueMarketQuotationEvent {
    private final String id;
    private final MarketQuotation marketQuotation;

    public IssueMarketQuotationEvent(String id, MarketQuotation marketQuotation) {
        this.id = id;
        this.marketQuotation = marketQuotation;
    }

    public MarketQuotation getMarketQuotation() {
        return marketQuotation;
    }
}
