package cn.pipipan.eisproject.brokergatewayddd.repository;

import cn.pipipan.eisproject.brokergatewayddd.domain.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TraderRepository extends MongoRepository<Trader, String> {
    public Trader findTraderByTraderNameEqualsAndPasswordEquals(String traderName, String password);
}
