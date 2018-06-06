# 快速使用文档

## aspect目录

AOP后台登录登出切面，监控检查控制层以Sb开头的类的所有方法，除了本身的登入登出方法，通过查询Cookie携带的信息，与Redis存储的登录信息进行判断。

## config目录

项目配置，微信公众平台配置、微信开放平台配置、微信支付配置、WebSocket配置

## constant目录

Cookie及Redis使用到的常量

## controller目录

数据对接、网页返回等

## converter目录

数据类型转换

##dataobject目录

dao、mapper文件是项目对MyBatis的支持Demo，详细请看[二次开发文档](dev2.md)，其余文件是基于JPA，数据库表对应的类

## dto目录

数据传输层

## enums目录

对应的类型状态

## exception目录

Demo系统定义异常

## form目录

前端视图提交数据对象类

## handler目录

异常拦截

## repositiry目录

JPA DAO 层，数据增删改查

## service目录

逻辑层

## utils目录

工具类

## vo目录

视图数据类

## resources/mapper目录

系统支持Mybatis，XMl化演示，详细请看二次开发文档

## resources/static目录

系统静态资源存放

## resources/templates目录

Freemarker模板开发，使用http://www.ibootstrap.cn/ 网站的拖拽化快速生成前端H5代码，构建自定义管理系统




