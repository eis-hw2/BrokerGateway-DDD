package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.springframework.beans.BeanUtils;

public class OrderBlotter {
    static class Convert implements DTOConvert<OrderBlotter, OrderBlotterDTO> {
        @Override
        public OrderBlotterDTO convertFrom(OrderBlotter orderBlotter) {
            OrderBlotterDTO res = new OrderBlotterDTO();
            BeanUtils.copyProperties(orderBlotter, res);
            return res;
        }
    }

    public OrderBlotterDTO convertToOrderBlotterDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    private String id;
    private int count;
    private int price;
    private String creationTime;
    private String buyerTraderName;
    private String sellerTraderName;

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

    public OrderBlotter(String id, OrderDTO order1, OrderDTO order2, int count){
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

    protected OrderBlotter(){

    }
}
