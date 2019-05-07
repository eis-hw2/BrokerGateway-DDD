package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.InsertLimitOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueLimitOrderEvent;
import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class LimitOrder {
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
    private String futureId;
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
        AggregateLifecycle.apply(new InsertLimitOrderEvent(this.futureId, this.convertToLimitOrderDTO()));
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

    public String getFutureId() {
        return futureId;
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

    public Status getStatus() {
        return status;
    }
}
