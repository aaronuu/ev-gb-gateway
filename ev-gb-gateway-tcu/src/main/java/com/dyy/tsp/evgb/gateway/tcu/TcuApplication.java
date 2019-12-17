package com.dyy.tsp.evgb.gateway.tcu;

import com.dyy.tsp.evgb.gateway.tcu.config.TcuProperties;
import com.dyy.tsp.evgb.gateway.tcu.netty.TcuNettyClient;
import com.dyy.tsp.kafka.config.KafkaProperties;
import com.dyy.tsp.kafka.config.ProducerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = {TcuProperties.class, KafkaProperties.class, ProducerProperties.class})
@SpringBootApplication
@EnableDiscoveryClient
public class TcuApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TcuApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Autowired
    private TcuProperties tcuProperties;

    @Bean
    public TcuNettyClient client() {
        TcuNettyClient client = new TcuNettyClient(tcuProperties.getServerHost(),tcuProperties.getServerPort());
        client.setTimeout(tcuProperties.getTimeout());
        client.setConnectMaxNum(tcuProperties.getReconnectMaxNum());
        return client;
    }
}
