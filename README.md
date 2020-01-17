# ev-gb-gateway 基于GB32960协议数据接入网关

#支持百万车辆以上!核心链路弹性运维!建议K8S部署!使用云中间件!推荐阿里云!
#网关安全:专网!建议TLS单向认证保证安全!

ev-gb-gateway-server

	功能:TSP数据接入网关
	主要技术:Netty,Kafka,内存+Redis二级缓存/订阅发布
	特点:高性能,高并发,高可用,支持K8S同POD多副本集群部署,横向拓展扩容
	808协议的压测简介:16C32G-IO密集型机器实测单节点TPS稳定高达50000/s
    GB32960的压测待验证！


ev-gb-gateway-tcu

    功能:模拟Tbox终端与TSP联调测试,提供RestFul接口给测试人员使用,降低测试成本。提供实时在线监控车辆功能
	主要技术:Netty,Redis缓存/订阅发布,Websocket
	特点:仿真模拟,降低测试成本,提供实时在线监控车辆功能


ev-gb-gateway-dispatcher

	功能:将接入数据标准封装成业务对象分发到不同Topic
	主要技术:Kafka,Redis缓存
	特点:纯内存解析,速度快。高性能,高并发,高可用,横向拓展扩容

ev-gb-gateway-protocol

	功能:GB32960协议抽象封装
	
ev-gb-gateway-document

	功能:文档


