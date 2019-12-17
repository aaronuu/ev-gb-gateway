package com.dyy.tsp.evgb.gateway.tcu.handler;

import com.dyy.tsp.evgb.gateway.protocol.entity.EvGBProtocol;
import com.dyy.tsp.evgb.gateway.protocol.handler.AbstractBusinessHandler;
import com.dyy.tsp.evgb.gateway.protocol.handler.IHandler;
import com.dyy.tsp.evgb.gateway.tcu.enumtype.TcuCoreType;
import io.netty.channel.Channel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 业务处理核心类
 * created by dyy
 */
@Service
public class BusinessHandler extends AbstractBusinessHandler implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void doBusiness(EvGBProtocol protrocol, Channel channel) {
        TcuCoreType tcuCoreType = TcuCoreType.valuesOf(protrocol.getCommandType().getId());
        if(tcuCoreType.getHandler()!=null){
            IHandler handler = (IHandler) applicationContext.getBean(tcuCoreType.getHandler());
            handler.doBusiness(protrocol,channel);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
