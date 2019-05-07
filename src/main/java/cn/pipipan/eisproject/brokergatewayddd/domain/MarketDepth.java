package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class MarketDepth {
    static class Convert implements DTOConvert<MarketDepth, MarketDepthDTO>{
        @Override
        public MarketDepthDTO convertFrom(MarketDepth marketDepth) {
            MarketDepthDTO marketDepthDTO = new MarketDepthDTO();
            return marketDepthDTO;
        }
    }

    public MarketDepthDTO convertToMarketDepthDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private class OrderPriceComposite{
        int price;
        List<LimitOrder> limitOrders;
    }

    private List<OrderPriceComposite> sellers;
    private List<OrderPriceComposite> buyers;

    @AggregateIdentifier
    private String id;

    public MarketDepth(String id){
        this.id = id;
        this.sellers = new ArrayList<>();
        this.buyers = new ArrayList<>();
    }

    protected MarketDepth(){

    }
}
