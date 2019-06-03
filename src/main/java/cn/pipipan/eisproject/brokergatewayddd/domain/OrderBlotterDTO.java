package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class OrderBlotterDTO {
    @Id
    private String id;
    private int count;
    private int price;
    private String creationTime;
    private String buyerTraderName;
    private String sellerTraderName;
    private String buyerOrderId;
    private String sellerOrderId;
    private String marketDepthId;

    public static OrderBlotterDTO createOrderBlotter(int delta, int price, OrderDTO buyer, OrderDTO seller, String marketDepthId){
        OrderBlotterDTO orderBlotterDTO = new OrderBlotterDTO();
        orderBlotterDTO.setId(UUID.randomUUID().toString());
        orderBlotterDTO.setCount(delta);
        orderBlotterDTO.setPrice(price);
        orderBlotterDTO.setCreationTime(Util.getDate(new Date()));
        orderBlotterDTO.setBuyerTraderName(buyer.getTraderName()); orderBlotterDTO.setSellerTraderName(seller.getTraderName());
        orderBlotterDTO.setBuyerOrderId(buyer.getId()); orderBlotterDTO.setSellerOrderId(seller.getId());
        orderBlotterDTO.setMarketDepthId(marketDepthId);
        return orderBlotterDTO;
    }
    public String getBuyerOrderId() {
        return buyerOrderId;
    }

    public void setBuyerOrderId(String buyerOrderId) {
        this.buyerOrderId = buyerOrderId;
    }

    public String getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(String sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public String getBuyerTraderName() {
        return buyerTraderName;
    }

    public void setBuyerTraderName(String buyerTraderName) {
        this.buyerTraderName = buyerTraderName;
    }

    public String getSellerTraderName() {
        return sellerTraderName;
    }

    public void setSellerTraderName(String sellerTraderName) {
        this.sellerTraderName = sellerTraderName;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
