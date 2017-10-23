# test-spring-boot
self project 
## config-client
spring-cloud-config-server demo实现，已使用spring-cloud-bus实现动态刷新配置功能
## config-server
config-server,与git项目、spring-cloud-bus集成实现动态刷新配置
## eureka
spring-cloud生态圈中的服务治理
### eureka-client
eureka客户端，注册实例，服务的提供方
### eureka-consumer
eureka消费者，服务的消费放
### eureka-server
eureka server，类似于zoo keeper
## hystrix
熔断器demo实现
### hystrix-common
熔断器的通用实现。
1.调用超时，服务降级
2.定制化异常处理逻辑
3.失败转移，及重试机制
### hystrix-dashboard
spring-cloud生态圈中的监控实现（按书本实现的逻辑代码，但是一直未显示监测信息，有待进一步研究）
## rabbitmq-hello
linux环境安装rabbit，使用spring-boot实现的简单的消息推送与消费的demo
## rest-service
spring-boot实现的restful的web项目
## spring-stream
### cloud-stream-hello
spring-cloud实现的stream项目
### spring-integration
spring-integration实现的消息绑定通道的消息推送与处理
## test-service
## turbine
spring-cloud项目的监控，包括健康检查、项目参数信息的查看
