package cn.pipipan.eisproject.brokergatewayddd.domain;

import javafx.util.Pair;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class MarketDepthDTO {
    @Id
    String id;
    List<Pair<Integer, Integer>> buyers = new ArrayList<>();
    List<Pair<Integer, Integer>> sellers = new ArrayList<>();

    public MarketDepthDTO() {
    }

    public List<Pair<Integer, Integer>> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Pair<Integer, Integer>> buyers) {
        this.buyers = buyers;
    }

    public List<Pair<Integer, Integer>> getSellers() {
        return sellers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSellers(List<Pair<Integer, Integer>> sellers) {
        this.sellers = sellers;
    }
}
