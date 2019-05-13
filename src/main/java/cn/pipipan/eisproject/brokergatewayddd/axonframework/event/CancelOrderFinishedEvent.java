package cn.pipipan.eisproject.brokergatewayddd.axonframework.event;

import cn.pipipan.eisproject.brokergatewayddd.domain.Status;

public class CancelOrderFinishedEvent {
    final String id;
    final String cancelOrderId;
    final Status targetStatus;

    public String getId() {
        return id;
    }

    public String getCancelOrderId() {
        return cancelOrderId;
    }

    public CancelOrderFinishedEvent(String id, String cancelOrderId, Status targetStatus) {
        this.id = id;
        this.cancelOrderId = cancelOrderId;
        this.targetStatus = targetStatus;
    }

    public Status getTargetStatus() {
        return targetStatus;
    }
}
