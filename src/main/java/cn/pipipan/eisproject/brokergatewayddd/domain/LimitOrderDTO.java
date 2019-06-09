package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LimitOrderDTO implements OrderDTO{
    static class Convert implements DTOConvert<LimitOrderDTO, LimitOrder> {

        @Override
        public LimitOrder convertFrom(LimitOrderDTO limitOrderDTO) {
            LimitOrder limitOrder = new LimitOrder();
            BeanUtils.copyProperties(limitOrderDTO, limitOrder);
            return limitOrder;
        }
    }

    public LimitOrder convertToLimitOrder(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }
    @Id
    private String id;
    @ApiModelProperty(required = true)
    private String futureName;
    private String marketDepthId;
    @ApiModelProperty(required = true)
    private int totalCount;
    private int count;
    @ApiModelProperty(required = true)
    private int unitPrice;
    @ApiModelProperty(required = true)
    private Side side;
    private Status status;
    private String creationTime;
    private String traderName;
    @ApiModelProperty(required = true)
    private String clientId;
    private String statusSwitchTime;

    public String getStatusSwitchTime() {
        return statusSwitchTime;
    }

    public void setStatusSwitchTime(String statusSwitchTime) {
        this.statusSwitchTime = statusSwitchTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setId(String id) {
        this.id = id;
    }
}
