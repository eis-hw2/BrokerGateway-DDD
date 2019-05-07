package cn.pipipan.eisproject.brokergatewayddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BrokergatewayDddApplication {
    public static ApplicationContext ac;
    public static void main(String[] args) {
        ac = SpringApplication.run(BrokergatewayDddApplication.class, args);
    }

}
