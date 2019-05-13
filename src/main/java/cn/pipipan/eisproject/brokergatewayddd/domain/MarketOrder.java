package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.springframework.beans.BeanUtils;

public class MarketOrder {

    public void decreaseCount(int delta) {
        count -= delta;
        if (count == 0) this.status = Status.FINISHED;
    }


    static class Convert implements DTOConvert<MarketOrder, MarketOrderDTO> {
        @Override
        public MarketOrderDTO convertFrom(MarketOrder marketOrder) {
            MarketOrderDTO marketOrderDTO = new MarketOrderDTO();
            BeanUtils.copyProperties(marketOrder, marketOrderDTO);
            return marketOrderDTO;
        }
    }

    public MarketOrderDTO convertToMarketOrderDTO(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }

    String id;
    private String marketDepthId;
    private int count;
    private Side side;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public boolean isBuyer(){
        return this.side.equals(Side.BUYER);
    }
    public boolean isSeller() {
        return this.side.equals(Side.SELLER);
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
