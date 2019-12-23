# ev-gb-gateway 基于GB32960协议数据接入网关

#支持百万车辆以上!核心链路弹性运维!建议K8S部署!使用云中间件!推荐阿里云!
#网关安全:专网!建议TLS单向认证保证安全!

ev-gb-gateway-server

	功能:TSP数据接入网关
	主要技术:Netty,Kafka,内存+Redis二级缓存/订阅发布
	特点:高性能,高并发,高可用,支持K8S同POD多副本集群部署,横向拓展扩容
	808协议的压测简介:基于16c32g云虚拟主机。JVM内存给的8G!
	        IO密集型机器实测单节点TPS稳定高达24000/s。(扩大内存到24G,预估能上50000+/s
	        参考Netty项目实战优化文档1.0.docx(808协议的压测)
	        GB32960的压测待验证！！！808是我干过最垃圾的协议！
	        与808压测同等条件下,自定义通讯协议TPS可以上60000+/s


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


dyy-boot-framework  自封装微服务脚手架

	详细参考:https://github.com/15000814726/dyy-boot-framework.git
	目前是私有仓库,可联系微信:dyy930718
    <parent>
        <groupId>com.dyy.tsp</groupId>
        <artifactId>dyy-boot-framework</artifactId>
        <version>1.1.2-RELEASE</version>
    </parent>
    配置中心/注册中心都用Nacos
    目前已完成Netty,Redis,Kafka,MongoDb,Swagger,Nacos,ES,第三方高德地图,第三方腾讯云/阿里云存储的封装。(已实测)
    后期陆续完善脚手架,Hbase,mysql,微服务网关熔断/限流
  

你需要掌握以下技能:

	1.springboot,springcloud
    2.netty NIO编程
    3.redis,kafka,es,moongo,hbase,canal,mysql各种中间件
    4.poi 报表
    5.websocket
    6.第三方高德地图对接
    7.第三方阿里云/腾讯云存储
    8.自建Maven私服
    9.自建Nacos注册/配置中心
    ................


