package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.BrokergatewayDddApplication;
import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class LimitOrder {
    Logger logger = LoggerFactory.getLogger(LimitOrder.class);
    CommandGateway commandGateway = BrokergatewayDddApplication.ac.getBean(CommandGateway.class);
    public static enum Side {
        BUYER,
        SELLER
    }
    public static enum Status{
        WAITING,
        FINISHED
    }
    static class Convert implements DTOConvert<LimitOrder, LimitOrderDTO>{
        @Override
        public LimitOrderDTO convertFrom(LimitOrder limitOrder) {
            LimitOrderDTO limitOrderDTO = new LimitOrderDTO();
            BeanUtils.copyProperties(limitOrder, limitOrderDTO);
            return limitOrderDTO;
        }
    }

    public LimitOrderDTO convertToLimitOrderDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private String id;
    private String marketDepthId;
    private int count;
    private int unitPrice;
    private Side side;
    private Status status;

    public void decreaseCount(int delta){
        this.count -= delta;
        if (this.count == 0) this.status = Status.FINISHED;
    }

    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }

    public String getId() {
        return id;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public int getCount() {
        return count;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public Side getSide() {
        return side;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
