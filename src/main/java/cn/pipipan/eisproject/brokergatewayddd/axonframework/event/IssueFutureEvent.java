package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.FutureDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class IssueFutureEvent {
    @TargetAggregateIdentifier
    String id;
    FutureDTO future;

    public IssueFutureEvent(String id, FutureDTO future) {
        this.id = id;
        this.future = future;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FutureDTO getFutureDTO() {
        return future;
    }

    public void setFutureDTO(FutureDTO future) {
        this.future = future;
    }
}
