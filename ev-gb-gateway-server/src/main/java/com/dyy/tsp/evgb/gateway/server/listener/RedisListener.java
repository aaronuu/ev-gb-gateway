package com.dyy.tsp.evgb.gateway.server.listener;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.redis.config.RedisConfig;
import com.dyy.tsp.redis.config.RedisProperties;
import com.dyy.tsp.redis.enumtype.LibraryType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SuppressWarnings("all")
@Configuration
@ConditionalOnProperty("redis.listener.subscribe")
public class RedisListener {

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisProperties redisProperties;

    @Value("${redis.listener.subscribe}")
    private String topics;

    /**
     * redis消息监听器容器
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(@Qualifier("listenerAdapter") MessageListenerAdapter listenerAdapter) {
        if(StringUtils.isBlank(topics)){
            throw new BusinessException("init AbstartRedisListener topics is not blank");
        }
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        RedisConnectionFactory rf = redisConfig.connectionFactory(
                redisProperties.getHostName(),redisProperties.getPort(),redisProperties.getPassword(),redisProperties.getMaxIdle(),
                redisProperties.getMaxTotal(), LibraryType.COMMAND.getDatabase(),redisProperties.getMaxWaitMillis(),redisProperties.getTestOnBorrow());
        container.setConnectionFactory(rf);
        String[] split = topics.split(",");
        for (String topic:split) {
            container.addMessageListener(listenerAdapter, new PatternTopic(topic));
        }
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     * @param handler
     * @return
     */
    @Bean("listenerAdapter")
    public MessageListenerAdapter listenerAdapter(CommandDownHandler handler) {
        return new MessageListenerAdapter(handler, "doBusiness");
    }
}
