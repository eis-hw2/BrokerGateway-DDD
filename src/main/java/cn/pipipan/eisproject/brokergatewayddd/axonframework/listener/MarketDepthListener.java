package cn.pipipan.eisproject.brokergatewayddd.axonframework.listener;

import cn.pipipan.eisproject.brokergatewayddd.axonframework.event.MarketDepthFixedEvent;
import cn.pipipan.eisproject.brokergatewayddd.repository.MarketDepthDTORepository;
import com.alibaba.fastjson.JSON;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketDepthListener {
    @Autowired
    MarketDepthDTORepository marketDepthDTORepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @EventHandler
    public void on(MarketDepthFixedEvent marketDepthFixedEvent){
        rabbitTemplate.convertAndSend("marketDepth", "marketDepth", JSON.toJSONString(marketDepthFixedEvent.getMarketDepthDTO()));
        marketDepthDTORepository.save(marketDepthFixedEvent.getMarketDepthDTO());
    }
}
