package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueFutureCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueFutureEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class Future {
    @AggregateIdentifier
    String id;
    String marketDepthId;
    String description;

    @CommandHandler
    public Future(IssueFutureCommand issueFutureCommand){
        AggregateLifecycle.apply(new IssueFutureEvent(issueFutureCommand.getId(), issueFutureCommand.getFutureDTO()));
        try {
            AggregateLifecycle.createNew(MarketDepth.class, () -> new MarketDepth(issueFutureCommand.getFutureDTO().getMarketDepthId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventSourcingHandler
    public void on(IssueFutureEvent issueFutureEvent){
        BeanUtils.copyProperties(issueFutureEvent.getFutureDTO(), this);
        this.id = issueFutureEvent.getId();
    }

    protected Future(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
