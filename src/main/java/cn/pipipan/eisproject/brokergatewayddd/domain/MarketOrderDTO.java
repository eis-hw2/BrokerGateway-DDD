package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MarketOrderDTO implements OrderDTO{
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

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
    @ApiModelProperty(required = true)
    private Side side;
    private String creationTime;
    private String traderName;
    private Status status;
    @ApiModelProperty(required = true)
    private int totalCount;
    @ApiModelProperty(required = true)
    private String futureName;
    @ApiModelProperty(required = true)
    private String clientId;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public String getFutureName() {
        return futureName;
    }

    public void setFutureName(String futureName) {
        this.futureName = futureName;
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
