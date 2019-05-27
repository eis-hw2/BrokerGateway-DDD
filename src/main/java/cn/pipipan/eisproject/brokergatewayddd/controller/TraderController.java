package cn.pipipan.eisproject.brokergatewayddd.controller;

import cn.pipipan.eisproject.brokergatewayddd.domain.Trader;
import cn.pipipan.eisproject.brokergatewayddd.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraderController {
    @Autowired
    TraderRepository traderRepository;

    @PostMapping("trader")
    public Trader signUp(Trader trader){
        return traderRepository.save(trader);
    }
}
