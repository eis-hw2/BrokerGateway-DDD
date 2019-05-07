package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.BrokergatewayDddApplication;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.InsertLimitOrderCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.MarketDepthChangedCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.MarketDepthFixedCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.*;
import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import javafx.util.Pair;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class MarketDepth {
    Logger logger = LoggerFactory.getLogger(MarketDepth.class);
    CommandGateway commandGateway = BrokergatewayDddApplication.ac.getBean(CommandGateway.class);

    class Convert implements DTOConvert<MarketDepth, MarketDepthDTO>{
        @Override
        public MarketDepthDTO convertFrom(MarketDepth marketDepth) {
            MarketDepthDTO marketDepthDTO = new MarketDepthDTO();
            marketDepthDTO.id = marketDepth.id;
            for (int i=marketDepth.buyers.size()-1; i>=Math.max(0, marketDepth.buyers.size()-3); i--){
                OrderPriceComposite orderPriceComposite = buyers.get(i);
                marketDepthDTO.buyers.add(new Pair(orderPriceComposite.getLimitOrders().stream().mapToInt(LimitOrder::getCount).sum(), orderPriceComposite.getPrice()));
            }
            for (int i=0; i<Math.min(3, marketDepth.sellers.size()); i++){
                OrderPriceComposite orderPriceComposite = sellers.get(i);
                marketDepthDTO.sellers.add(new Pair(orderPriceComposite.getLimitOrders().stream().mapToInt(LimitOrder::getCount).sum(), orderPriceComposite.getPrice()));
            }
            return marketDepthDTO;
        }
    }

    public MarketDepthDTO convertToMarketDepthDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private class OrderPriceComposite{
        int price;
        List<LimitOrder> limitOrders;

        OrderPriceComposite() {
        }

        OrderPriceComposite(LimitOrder limitOrder){
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

    private List<OrderPriceComposite> sellers;
    private List<OrderPriceComposite> buyers;

    @AggregateIdentifier
    private String id;

    public MarketDepth(String id){
        AggregateLifecycle.apply(new IssueMarketDepthEvent(id));
    }

    protected MarketDepth(){

    }

    @CommandHandler
    public void handle(InsertLimitOrderCommand insertLimitOrderCommand){
        logger.info("onInsertLimitOrderCommand: {}", insertLimitOrderCommand.getLimitOrderDTO());
        AggregateLifecycle.apply(new InsertLimitOrderEvent(insertLimitOrderCommand.getId(), insertLimitOrderCommand.getLimitOrderDTO()));
    }

    @CommandHandler
    public void handle(MarketDepthChangedCommand marketDepthChangedCommand){
//        AggregateLifecycle.apply(new MarketDepthChangedEvent(marketDepthChangedCommand.getId()));
    }

    @EventSourcingHandler
    public void on(IssueMarketDepthEvent issueMarketDepthEvent){
        this.id = issueMarketDepthEvent.getId();
        this.buyers = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(InsertLimitOrderEvent insertLimitOrderEvent){
        logger.info("onInsertLimitOrderEvent {}", insertLimitOrderEvent.getLimitOrderDTO());
        LimitOrder limitOrder = insertLimitOrderEvent.getLimitOrderDTO().convertToLimitOrder();
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
        logger.info("index: {}", index);
        insertIntoWaitingQueue(order, waitingComposites, index);
    }

    private int binaryFindIndex(LimitOrder order, List<OrderPriceComposite> waitingComposites) {
        //TODO 使用二分查找
        for (int i=0; i<waitingComposites.size(); ++i){
            if (waitingComposites.get(i).getPrice() >= order.getUnitPrice()) return i;
        }
        return waitingComposites.size();
    }

    @EventSourcingHandler
    public void on(MarketDepthChangedEvent marketDepthChangedEvent){
       if (isFixed()) commandGateway.send(new MarketDepthFixedCommand(id, convertToMarketDepthDTO()));
//        else {
//            OrderPriceComposite buyer = buyers.get(buyers.size() - 1);
//            OrderPriceComposite seller = sellers.get(0);
//            LimitOrder buyer_order = buyer.getLimitOrders().get(0);
//            LimitOrder seller_order = seller.getLimitOrders().get(0);
//            int delta = Math.min(buyer_order.getCount(), seller_order.getCount());
//            decreaseCount(buyer_order, delta);
//            decreaseCount(seller_order, delta);
//            //TODO 生成订单
//            AggregateLifecycle.apply(new MarketDepthChangedEvent(id));
//        }
    }

    private void decreaseCount(LimitOrder order, int delta) {
        order.decreaseCount(delta);
        List<OrderPriceComposite> waitingComposites = order.isBuyer() ? buyers : sellers;
        int index = order.isBuyer() ? buyers.size() - 1 : 0;
        if (order.getCount() == 0) {
            if (order.getSide() == LimitOrder.Side.BUYER){
                List<LimitOrder> limitOrders = waitingComposites.get(index).getLimitOrders();
                limitOrders.remove(0);
                if (limitOrders.isEmpty()) waitingComposites.remove(index);
            }
        }
        AggregateLifecycle.apply(new LimitOrderDecreaseCountEvent(order.getId(), delta));
    }

    private boolean isFixed(){
        return buyers.isEmpty() || sellers.isEmpty() || buyers.get(buyers.size()-1).getPrice() < sellers.get(0).getPrice();
    }
}
