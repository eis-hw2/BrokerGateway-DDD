package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.*;
import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aggregate(snapshotTriggerDefinition = "mySnapshotTriggerDefinition")
public class MarketDepth {
    Logger logger = LoggerFactory.getLogger(MarketDepth.class);
    @Autowired
    CommandGateway commandGateway;

    class Convert implements DTOConvert<MarketDepth, MarketDepthDTO> {
        @Override
        public MarketDepthDTO convertFrom(MarketDepth marketDepth) {
            MarketDepthDTO marketDepthDTO = new MarketDepthDTO();
            marketDepthDTO.id = marketDepth.id;
            for (int i = marketDepth.buyers.size() - 1; i >= Math.max(0, marketDepth.buyers.size() - 3); i--) {
                OrderPriceComposite orderPriceComposite = buyers.get(i);
                marketDepthDTO.addBuyer(orderPriceComposite.getLimitOrders().stream().mapToInt(LimitOrder::getCount).sum(), orderPriceComposite.getPrice());
            }
            for (int i = 0; i < Math.min(3, marketDepth.sellers.size()); i++) {
                OrderPriceComposite orderPriceComposite = sellers.get(i);
                marketDepthDTO.addSeller(orderPriceComposite.getLimitOrders().stream().mapToInt(LimitOrder::getCount).sum(), orderPriceComposite.getPrice());
            }
            return marketDepthDTO;
        }
    }

    public MarketDepthDTO convertToMarketDepthDTO() {
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private class OrderPriceComposite {
        int price;
        List<LimitOrder> limitOrders;

        OrderPriceComposite() {
        }

        OrderPriceComposite(LimitOrder limitOrder) {
            this.price = limitOrder.getUnitPrice();
            this.limitOrders = new ArrayList<>();
            this.limitOrders.add(limitOrder);
        }

        int getPrice() {
            return price;
        }

        void setPrice(int price) {
            this.price = price;
        }

        List<LimitOrder> getLimitOrders() {
            return limitOrders;
        }

        void setLimitOrders(List<LimitOrder> limitOrders) {
            this.limitOrders = limitOrders;
        }

        void addOrder(LimitOrder order) {
            this.limitOrders.add(order);
        }
    }


    @AggregateIdentifier
    private String id;

    private List<OrderPriceComposite> sellers;
    private List<OrderPriceComposite> buyers;
    private List<OrderBlotter> orderBlotters;
    private Map<String, LimitOrder> limitOrders;

    public MarketDepth(String id) {
        AggregateLifecycle.apply(new IssueMarketDepthEvent(id));
    }

    @CommandHandler
    public void handle(IssueLimitOrderCommand issueLimitOrderCommand){
        AggregateLifecycle.apply(new IssueLimitOrderEvent(id, issueLimitOrderCommand.getLimitOrderDTO()));
    }

    @EventSourcingHandler
    public void on(IssueMarketDepthEvent issueMarketDepthEvent) {
        this.id = issueMarketDepthEvent.getId();
        this.buyers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.orderBlotters = new ArrayList<>();
        this.limitOrders = new HashMap<>();
    }

    @EventSourcingHandler
    public void on(IssueLimitOrderEvent issueLimitOrderEvent){
        logger.info("onInsertLimitOrderEvent");
        LimitOrderDTO limitOrderDTO = issueLimitOrderEvent.getLimitOrderDTO();
        limitOrders.put(limitOrderDTO.getId(), limitOrderDTO.convertToLimitOrder());
        // insert into waiting queue;
        LimitOrder limitOrder = limitOrderDTO.convertToLimitOrder();
        insertIntoWaitingQueue(limitOrder, limitOrder.isBuyer() ? buyers : sellers);
        AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
    }

    private void insertIntoWaitingQueue(LimitOrder order, List<OrderPriceComposite> waitingComposites, int i) {
        if (i == waitingComposites.size() || waitingComposites.get(i).getPrice() != order.getUnitPrice())
            waitingComposites.add(i, new OrderPriceComposite(order));
        else
            waitingComposites.get(i).addOrder(order);
    }

    private void insertIntoWaitingQueue(LimitOrder order, List<OrderPriceComposite> waitingComposites) {
        int index = binaryFindIndex(order, waitingComposites);
        insertIntoWaitingQueue(order, waitingComposites, index);
    }

    private int binaryFindIndex(LimitOrder order, List<OrderPriceComposite> waitingComposites) {
        //TODO 使用二分查找
        for (int i = 0; i < waitingComposites.size(); ++i) {
            if (waitingComposites.get(i).getPrice() >= order.getUnitPrice()) return i;
        }
        return waitingComposites.size();
    }

    @EventSourcingHandler
    public void on(MarketDepthChangedEvent marketDepthChangedEvent) {
        if (isFixed()) AggregateLifecycle.apply(new MarketDepthFixedEvent(id, convertToMarketDepthDTO()));
        else {
            OrderPriceComposite buyer = buyers.get(buyers.size() - 1);
            OrderPriceComposite seller = sellers.get(0);
            LimitOrder buyer_order = buyer.getLimitOrders().get(0);
            LimitOrder seller_order = seller.getLimitOrders().get(0);
            int delta = Math.min(buyer_order.getCount(), seller_order.getCount());
            decreaseCount(buyer_order, delta);
            decreaseCount(seller_order, delta);
            AggregateLifecycle.apply(new IssueOrderBlotterEvent(id, buyer_order.convertToLimitOrderDTO(), seller_order.convertToLimitOrderDTO(), delta));
            AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
        }
    }

    private void decreaseCount(LimitOrder order, int delta) {
        order.decreaseCount(delta);
        List<OrderPriceComposite> waitingComposites = order.isBuyer() ? buyers : sellers;
        int index = order.isBuyer() ? buyers.size() - 1 : 0;
        if (order.getCount() == 0) {
            List<LimitOrder> limitOrders = waitingComposites.get(index).getLimitOrders();
            limitOrders.remove(0);
            if (limitOrders.isEmpty()) waitingComposites.remove(index);
        }
        AggregateLifecycle.apply(new LimitOrderCountDecreasedEvent(id, order.getId(), delta));
    }

    private boolean isFixed() {
        return buyers.isEmpty() || sellers.isEmpty() || buyers.get(buyers.size() - 1).getPrice() < sellers.get(0).getPrice();
    }

    @EventSourcingHandler
    public void on(LimitOrderCountDecreasedEvent limitOrderCountDecreasedEvent){
        LimitOrder limitOrder = limitOrders.get(limitOrderCountDecreasedEvent.getOrderId());
        limitOrder.decreaseCount(limitOrderCountDecreasedEvent.getCount());
        AggregateLifecycle.apply(new LimitOrderChangedEvent(id, limitOrder.convertToLimitOrderDTO()));
    }

    @EventSourcingHandler
    public void on(IssueOrderBlotterEvent issueOrderBlotterEvent){
        //TODO 创建真正的OrderBlotter
        orderBlotters.add(new OrderBlotter());
    }

    protected MarketDepth() {

    }
}
