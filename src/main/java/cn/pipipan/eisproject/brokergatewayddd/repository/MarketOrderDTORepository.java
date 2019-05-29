package cn.pipipan.eisproject.brokergatewayddd.repository;

import cn.pipipan.eisproject.brokergatewayddd.domain.MarketOrderDTO;
import cn.pipipan.eisproject.brokergatewayddd.domain.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketOrderDTORepository extends MongoRepository<MarketOrderDTO, String> {
    public MarketOrderDTO findMarketOrderDTOById(String id);
    public List<MarketOrderDTO> findMarketOrderDTOSByCreationTimeEqualsAndStatusEquals(String date, Status status);
}
