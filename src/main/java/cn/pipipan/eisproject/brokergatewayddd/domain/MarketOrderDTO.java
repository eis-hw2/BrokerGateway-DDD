package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MarketOrderDTO implements OrderDTO{
    static class Convert implements DTOConvert<MarketOrderDTO, MarketOrder> {
        @Override
        public MarketOrder convertFrom(MarketOrderDTO marketOrderDTO) {
            MarketOrder marketOrder = new MarketOrder();
            BeanUtils.copyProperties(marketOrderDTO, marketOrder);
            return marketOrder;
        }
    }

    public MarketOrder convertToMarketOrder(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    @Id
    String id;
    private String marketDepthId;
    private int count;
    private Side side;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public int getCount() {
        return count;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setCount(int count) {
        this.count = count;
    }
}