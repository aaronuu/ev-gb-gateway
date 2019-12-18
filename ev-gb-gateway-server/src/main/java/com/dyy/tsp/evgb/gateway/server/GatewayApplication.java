package com.dyy.tsp.evgb.gateway.server;

import com.dyy.tsp.evgb.gateway.server.config.EvGBProperties;
import com.dyy.tsp.evgb.gateway.server.netty.EvGBNettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = {EvGBProperties.class})
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Autowired
    private EvGBProperties evGBProperties;

    @Bean
    public EvGBNettyServer server() {
        EvGBNettyServer server = new EvGBNettyServer();
        server.setPort(evGBProperties.getPort());
        server.setTimeout(evGBProperties.getTimeout());
        server.setSoBackLog(evGBProperties.getSoBackLog());
        server.setWorkLoopCount(evGBProperties.getWorkLoopCount());
        return server;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
