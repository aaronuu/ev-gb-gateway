# ev-gb-gateway 基于GB32960协议数据接入网关

#支持百万车辆以上!核心链路弹性运维!建议K8S部署!使用云中间件!推荐阿里云!
#网关安全:专网!建议TLS单向认证保证安全!

ev-gb-gateway-server

	功能:TSP数据接入网关

	主要技术:Netty,Kafka,内存+Redis二级缓存/订阅发布

	特点:高性能,高并发,高可用,支持K8S同POD多副本集群部署,横向拓展扩容
	
	测试简介:基于16c32g云虚拟主机,IO密集型机器实测单节点TPS稳定高达24000/s。
	        最长压测时间24小时。CPU利用率60-80%。内存无瓶颈！此时是最佳状态。
	        单节点随意支撑30万车辆，巅峰时刻至少可支撑40-50万车辆。
	        参考Netty项目实战优化文档1.0.docx


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


<parent>
    <groupId>com.dyy.tsp</groupId>
    <artifactId>dyy-boot-framework</artifactId>
    <version>1.1.1-RELEASE</version>
</parent>
dyy-boot-framework  自封装微服务脚手架
详细参考:https://github.com/15000814726/dyy-boot-framework.git
目前是私有仓库,需要源码可联系微信:dyy930718

你需要掌握技能如下:
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


