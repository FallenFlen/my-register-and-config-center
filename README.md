配置中心基本功能
1. 配置服务-提供配置查询api
2. 提供starter sdk给客户端  
a. sdk支持需要接入配置服务  
b. sdk需要调用api获取配置  
c. 配置发生变更后，使用方刷新env，实现动态更新配置
3. 获取的配置加载进spring上下文
4. 能通过@Value注解动态获取配置中心的配置(yml)
5. 如果@Value无法通过配置中心获取，则通过本地配置获取
6. 支持@ConfigurationProperties
7. 支持.properties配置文件  

注册中心基本功能
1. 