package cn.pipipan.eisproject.brokergatewayddd.repository;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketDepthDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDepthDTORepository extends MongoRepository<MarketDepthDTO, String> {

}
