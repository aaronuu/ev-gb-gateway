package com.dyy.tsp.evgb.gateway.tcu.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 在线监控网关节点某台设备实时上报报文信息
 */
@Controller
public class ConsoleServer {

    @GetMapping(value ="/console")
    public String console(HttpServletResponse response) {
        return "forward:/index.html";
    }
}
