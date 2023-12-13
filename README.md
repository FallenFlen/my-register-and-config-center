手写配置中心&注册中心，一个长期大坑  

目前打算先写一版配置中心  

基本功能
1. 配置服务-提供配置crud api
2. 提供starter sdk给客户端  
2.1 sdk支持需要接入配置服务  
2.2 sdk需要调用api获取配置
3. 获取的配置加载进spring上下文
4. 能通过@Value注解动态获取配置中心的配置(yml)

ex
1. 如果@Value无法通过配置中心获取，则通过本地配置获取
2. 支持@ConfigurationProperties
3. 支持.properties配置文件