package cn.pipipan.eisproject.brokergatewayddd.repository;

import cn.pipipan.eisproject.brokergatewayddd.domain.CancelOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CancelOrderRepository extends MongoRepository<CancelOrder, String> {
    public CancelOrder findCancelOrderById(String id);
}
