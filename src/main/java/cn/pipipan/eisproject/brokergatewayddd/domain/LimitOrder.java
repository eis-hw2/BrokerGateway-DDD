package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.BrokergatewayDddApplication;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.InsertLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueLimitOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Aggregate
public class LimitOrder {
    Logger logger = LoggerFactory.getLogger(LimitOrder.class);
    CommandGateway commandGateway = BrokergatewayDddApplication.ac.getBean(CommandGateway.class);
    static enum Side {
        BUYER,
        SELLER
    }
    static enum Status{
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

    @AggregateIdentifier
    private String id;
    private String marketDepthId;
    private int count;
    private int unitPrice;
    private Side side;
    private Status status;

    @CommandHandler
    public LimitOrder(IssueLimitOrderCommand issueLimitOrderCommand){
        AggregateLifecycle.apply(new IssueLimitOrderEvent(issueLimitOrderCommand.getId(), issueLimitOrderCommand.getLimitOrderDTO()));
    }

    @EventSourcingHandler
    public void on(IssueLimitOrderEvent issueLimitOrderEvent){
        BeanUtils.copyProperties(issueLimitOrderEvent.getLimitOrderDTO(), this);
        this.id = issueLimitOrderEvent.getId();
        commandGateway.send(new InsertLimitOrderCommand(marketDepthId, convertToLimitOrderDTO()));
    }

    protected LimitOrder(){

    }

    public void decreaseCount(int delta){
        this.count -= delta;
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
