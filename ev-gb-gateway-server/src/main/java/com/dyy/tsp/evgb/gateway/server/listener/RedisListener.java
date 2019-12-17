package com.dyy.tsp.evgb.gateway.server.listener;

import com.dyy.tsp.common.exception.BusinessException;
import com.dyy.tsp.redis.common.CommonRedisCache;
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
import org.springframework.data.redis.core.RedisTemplate;
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
            throw new BusinessException("init redis container topics is not blank");
        }
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        RedisTemplate<String, String> redisTemplate = CommonRedisCache.redisTemplateMap.get(LibraryType.COMMAND.getDatabase());
        if(redisTemplate == null){
            throw new BusinessException("init redis container redisTemplate is not blank,must be 15 database");
        }
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
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
