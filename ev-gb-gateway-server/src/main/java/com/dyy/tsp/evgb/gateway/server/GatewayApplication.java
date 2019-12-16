package com.dyy.tsp.evgb.gateway.server;

import com.dyy.tsp.evgb.gateway.server.config.EvGBProperties;
import com.dyy.tsp.evgb.gateway.server.netty.EvGbGatewayServer;
import com.dyy.tsp.kafka.config.KafkaProperties;
import com.dyy.tsp.kafka.config.ProducerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = {EvGBProperties.class, KafkaProperties.class, ProducerProperties.class})
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Autowired
    private EvGBProperties evGBProperties;

    @Bean
    public EvGbGatewayServer evGbGatewayServer() {
        EvGbGatewayServer evGbGatewayServer = new EvGbGatewayServer();
        evGbGatewayServer.setPort(evGBProperties.getPort());
        evGbGatewayServer.setTimeout(evGBProperties.getTimeout());
        evGbGatewayServer.setSoBackLog(evGBProperties.getSoBackLog());
        evGbGatewayServer.setWorkLoopCount(evGBProperties.getWorkLoopCount());
        return evGbGatewayServer;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
