package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.helper.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class MarketQuotation {
    private float lastClosePrice;
    private float openPrice;
    private float closePrice;
    private float highPrice;
    private float lowPrice;
    private float currentPrice;
    private float changePrice;
    private float changePercent;
    private int totalVolume;
    private int totalShare = 1000;
    private float turnoverRate;
    private String date;
    private String marketDepthId;
    @Id
    private String id;

    MarketQuotation(String currentDate, float lastClosePrice, String marketDepthId){
        setDate(currentDate);
        setLastClosePrice(lastClosePrice);
        setOpenPrice(lastClosePrice);
        setHighPrice(lastClosePrice);
        setLowPrice(lastClosePrice);
        setId(UUID.randomUUID().toString());
        setMarketDepthId(marketDepthId);
    }

    public String getMarketDepthId() {
        return marketDepthId;
    }

    public void setMarketDepthId(String marketDepthId) {
        this.marketDepthId = marketDepthId;
    }

    public void update(OrderBlotterDTO orderBlotter){
        float price = orderBlotter.getPrice();
        int volume = orderBlotter.getCount();
        setTotalVolume(totalVolume+volume);
        setCurrentPrice(price);
        setChangePrice(currentPrice - lastClosePrice);
        setChangePercent(changePrice / lastClosePrice);
        setTurnoverRate(totalVolume/totalShare);
        if(price > highPrice || highPrice == 0){
            setHighPrice(price);
        }
        if(price < lowPrice || lowPrice == 0){
            setLowPrice(price);
        }
    }

    public MarketQuotation() {
    }

    public MarketQuotation(MarketQuotation other){
        this.openPrice = other.getClosePrice();
        this.id = UUID.randomUUID().toString();
        this.marketDepthId = other.getMarketDepthId();
        this.date = Util.getNowDate();
    }

    public MarketQuotation clone(){
        MarketQuotation marketQuotation = new MarketQuotation();
        BeanUtils.copyProperties(this, marketQuotation);
        return marketQuotation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public void setOpenPrice(float currentOpenPrice) {
        this.openPrice= currentOpenPrice;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public void setClosePrice(float ClosePrice) {
        this.closePrice= ClosePrice;
    }

    public float getClosePrice() {
        return closePrice;
    }

    public void setHighPrice(float HighPrice) {
        this.highPrice= HighPrice;
    }

    public float getHighPrice() {
        return highPrice;
    }

    public void setLowPrice(float LowPrice) {
        this.lowPrice= LowPrice;
    }

    public float getLowPrice() {
        return lowPrice;
    }

    public void setTotalVolume(int TotalVolume) {
        this.totalVolume= TotalVolume;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setLastClosePrice(float LastClosePrice) {
        this.lastClosePrice= LastClosePrice;
    }

    public float getLastClosePrice() {
        return lastClosePrice;
    }

    public void setCurrentPrice(float CurrentPrice) {
        this.currentPrice= CurrentPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setChangePrice(float ChangePrice) {
        this.changePrice= ChangePrice;
    }

    public float getChangePrice() {
        return changePrice;
    }

    public void setChangePercent(float ChangePercent) {
        this.changePercent= ChangePercent;
    }

    public float getChangePercent() {
        return changePercent;
    }

    public void setTotalShare(int TotalShare) {
        this.totalShare= TotalShare;
    }

    public float getTotalShare() {
        return totalShare;
    }

    public void setTurnoverRate(float TurnoverRate) {
        this.turnoverRate= TurnoverRate;
    }

    public float getTurnoverRate() {
        return turnoverRate;
    }

}

