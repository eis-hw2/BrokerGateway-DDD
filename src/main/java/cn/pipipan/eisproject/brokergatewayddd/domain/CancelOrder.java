package cn.pipipan.eisproject.brokergatewayddd.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Repository
public class CancelOrder implements OrderDTO{
    @Id
    String id;
    Type targetType;
    @ApiModelProperty(notes = "target for limit order")
    int unitPrice;
    @ApiModelProperty(notes = "target for limit order")
    Side side;
    String targetId;
    String marketDepthId;
    Status status;

    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public Status getStatus() {
        return status;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
