package cn.pipipan.eisproject.brokergatewayddd.repository;

import cn.pipipan.eisproject.brokergatewayddd.domain.FutureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureDTORepository extends MongoRepository<FutureDTO, String> {
}
