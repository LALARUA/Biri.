# Biri. 基于SpringCloud的分布式电商平台
## 一、技术框架：  
          1、SpringCloud:作为系统的基础框架，Springboot构建单个服务，Eureka作为服务注册中心，使用Feign发送Http请求实现服务间发现调用
          2、Mybatis：作为持久层框架，Mybatis动态sql更利于复杂查询的编写
          3、Redis ： 基于内存的非关系型数据库，本系统用来存储一些使用频率比较高的一些数据
          4、RabbitMq：高级消息队列，本系统目前用来异步发送信息和一些简单的分布式事务控制
## 二、服务拆分:  
          1、book-service：图书服务主要处理查询图书，复杂查询图书详情等业务，其中会调用评论服务调用该图书的评论  
          2、comment-service：评论服务主要处理图书评论回复等业务  
          3、eureka-server-1：服务注册主要为Eureka服务器，所有服务将自身注册到该Eureka服务器上
          4、login-register：认证服务主要为单点登录服务，采用Shiro进行认证授权，会调用用户服务获取用户信息和RabbitMq服务发送邮箱  
          5、user-service：用户服务主要为新增用户，查询用户等业务  
          6、order-cart:订单服务主要处理用户订单，购物车，收藏夹相关的业务,订单服务会调用图书服务获取图书信息
          7、rabbitmq-service：Rabbitmq服务目前在本系统只充当异步发送邮箱的功能，但因为是单独一个服务，易于扩展修改
          8、web-service：Web服务主要提供用户访问的Controller和html、css资源，通过用户的访问，调用其他服务得到Model数据，将其填充到View对象中再返回给用户  
          9、zuul:网关服务主要为Zuul服务作为微服务中的网关，提供给外部统一的访问入口

          
          
          
