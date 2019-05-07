package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.IssueFutureEvent;
import cn.pipipan.eisproject.brokergatewayddd.domain.FutureDTO;
import cn.pipipan.eisproject.brokergatewayddd.repository.FutureDTORepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FutureListener {
    @Autowired
    FutureDTORepository futureDTORepository;

    @EventHandler
    public void on(IssueFutureEvent issueFutureEvent){
        FutureDTO futureDTO = issueFutureEvent.getFutureDTO();
        futureDTO.setId(issueFutureEvent.getId());
        futureDTORepository.save(futureDTO);
    }
}
