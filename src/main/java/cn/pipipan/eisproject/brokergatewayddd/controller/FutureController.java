package cn.pipipan.eisproject.brokergatewayddd.controller;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.command.IssueFutureCommand;
import cn.pipipan.eisproject.brokergatewayddd.domain.FutureDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/futures")
public class FutureController {
    @Autowired
    CommandGateway commandGateway;

    @PostMapping
    public void addFuture(@RequestBody FutureDTO future){
        future.setMarketDepthId(UUID.randomUUID().toString());
        commandGateway.send(new IssueFutureCommand(UUID.randomUUID().toString(), future));
    }
}
