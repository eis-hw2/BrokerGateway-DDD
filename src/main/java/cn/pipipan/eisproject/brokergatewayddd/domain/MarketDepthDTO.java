package cn.pipipan.eisproject.brokergatewayddd.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class MarketDepthDTO {
    class Composite{
        int price;
        int count;

        public Composite(int count, int price) {
            this.price = price;
            this.count = count;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
    @Id
    String id;
    List<Composite> buyers = new ArrayList<>();
    List<Composite> sellers = new ArrayList<>();

    public MarketDepthDTO() {
    }

    public List<Composite> getBuyers() {
        return buyers;
    }

    public void addBuyer(int count, int price){
        this.buyers.add(new Composite(count, price));
    }

    public void addSeller(int count, int price){
        this.sellers.add(new Composite(count, price));
    }

    public void setBuyers(List<Composite> buyers) {
        this.buyers = buyers;
    }

    public List<Composite> getSellers() {
        return sellers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSellers(List<Composite> sellers) {
        this.sellers = sellers;
    }
}
