package cn.pipipan.eisproject.brokergatewayddd.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StopOrder implements OrderDTO{
    @Id
    private String id;
    @ApiModelProperty(required = true)
    private Type targetType;
    @ApiModelProperty(required = true)
    private String futureName;
    String marketDepthId;
    @ApiModelProperty(required = true)
    private int count;
    @ApiModelProperty(required = true)
    private Side side;
    @ApiModelProperty(notes = "如果是MarketOrder就不需要，如果是LimitOrder就需要", required = true)
    private int unitPrice;
    @ApiModelProperty(required = true, notes = "止损价格")
    private int stopPrice;
    Status status;
    private String creationTime;
    private String traderName;
    @ApiModelProperty(required = true)
    private String clientId;


    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }


    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public LimitOrder convertToLimitOrder(){
        LimitOrder limitOrder = new LimitOrder();
        BeanUtils.copyProperties(this, limitOrder);
        limitOrder.setTotalCount(this.getCount());
        return limitOrder;
    }

    public MarketOrder convertToMarketOrder(){
        MarketOrder marketOrder = new MarketOrder();
        BeanUtils.copyProperties(this, marketOrder);
        marketOrder.setTotalCount(this.getCount());
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

    public int getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(int stopPrice) {
        this.stopPrice = stopPrice;
    }

    public boolean isBuyer() {
        return this.side.equals(Side.BUYER);
    }

    public boolean isSeller() {
        return this.side.equals(Side.SELLER);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
