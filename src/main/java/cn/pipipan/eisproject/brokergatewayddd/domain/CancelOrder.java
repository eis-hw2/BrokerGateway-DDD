package cn.pipipan.eisproject.brokergatewayddd.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Repository
public class CancelOrder implements OrderDTO{
    @Id
    String id;
    Type targetType;
    @ApiModelProperty(notes = "为了快速找到LimitOrder位置, 如果是LimitOrder就填", required = true)
    int unitPrice;
    @ApiModelProperty(notes = "为了快速找到LimitOrder位置， 如果是LimitOrder就填", required = true)
    Side side;
    @ApiModelProperty(required = true)
    String targetId;
    @ApiModelProperty(required = true)
    private String futureName;
    String marketDepthId;
    Status status;
    String creationTime;
    String traderName;
    String statusSwitchTime;

    public String getStatusSwitchTime() {
        return statusSwitchTime;
    }

    public void setStatusSwitchTime(String statusSwitchTime) {
        this.statusSwitchTime = statusSwitchTime;
    }

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
