package cn.pipipan.eisproject.brokergatewayddd.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StopOrder implements OrderDTO{
    @Id
    String id;
    Type targetType;
    String marketDepthId;
    int count;
    Side side;
    int unitPrice;
    Status status;

    public LimitOrder convertToLimitOrder(){
        LimitOrder limitOrder = new LimitOrder();
        BeanUtils.copyProperties(this, limitOrder);
        return limitOrder;
    }

    public MarketOrder convertToMarketOrder(){
        MarketOrder marketOrder = new MarketOrder();
        BeanUtils.copyProperties(this, marketOrder);
        return marketOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getTargetType() {
        return targetType;
    }

    public void setTargetType(Type targetType) {
        this.targetType = targetType;
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

    public void setCount(int count) {
        this.count = count;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isBuyer() {
        return this.side.equals(Side.BUYER);
    }

    public boolean isSeller() {
        return this.side.equals(Side.SELLER);
    }
}