

# ev-gb-gateway Data Access Gateway based on GB32960 Protocol

# Supports over a million vehicles! Elastic O&M for core links! K8S deployment recommended! Uses cloud middleware! Alibaba Cloud recommended!
# Gateway Security: Dedicated network! Unidirectional TLS authentication is recommended to ensure security!

ev-gb-gateway-server

	Features: TSP Data Access Gateway
	Main Technologies: Netty, Kafka, In-memory + Redis two-level cache / Pub-Sub
	Characteristics: High performance, high concurrency, high availability. Supports cluster deployment with multiple replicas in the same POD on K8S. Horizontal scaling.
	808 Protocol Stress Test Overview: Tested on a 16-core 32GB IO-intensive machine, single-node TPS stably reaches up to 50,000/s.
    GB32960 stress test results pending verification!


ev-gb-gateway-tcu

    Features: Simulates T-Box terminals for joint debugging with TSP. Provides RESTful APIs for testers to reduce testing costs. Offers real-time online vehicle monitoring.
	Main Technologies: Netty, Redis cache / Pub-Sub, WebSocket
	Characteristics: Simulation, reduces testing costs, provides real-time online vehicle monitoring.


ev-gb-gateway-dispatcher

	Features: Standardizes incoming data into business objects and distributes them to different Topics.
	Main Technologies: Kafka, Redis cache
	Characteristics: Pure in-memory parsing, fast. High performance, high concurrency, high availability. Horizontal scaling.

ev-gb-gateway-protocol

	Features: Abstract encapsulation of the GB32960 protocol.
	
ev-gb-gateway-document

	Features: Documentation
