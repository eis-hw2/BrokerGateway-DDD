package cn.pipipan.eisproject.brokergatewayddd.scheduler;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.CloseMarketCommand;
import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.OpenMarketCommand;
import cn.pipipan.eisproject.brokergatewayddd.domain.FutureDTO;
import cn.pipipan.eisproject.brokergatewayddd.repository.FutureDTORepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    @Autowired
    FutureDTORepository futureDTORepository;
    @Autowired
    CommandGateway commandGateway;


    @Scheduled(cron = "0 0 23 * * *")
    public void scheduleMarketClose(){
        List<FutureDTO> futureDTOList = futureDTORepository.findAll();
        futureDTOList.forEach(f -> {
            commandGateway.send(new CloseMarketCommand(f.getMarketDepthId()));
        });
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void scheduleMarketOpen(){
        List<FutureDTO> futureDTOList = futureDTORepository.findAll();
        futureDTOList.forEach(f -> {
            commandGateway.send(new OpenMarketCommand(f.getMarketDepthId()));
        });
    }
}
