# Demo下载运行

## 数据库文件

执行sql文件夹下的diancan.sql文件，这是demo项目的数据，建议MySQL数据库为5.7以上，5.6可能存在时间数据格式问题

数据库表结构，可见![SQL文档](SQL.md)

## 设置配置

修改application-dev、application-prod中的配置信息，基本的数据库连接、Redis配置等

如果有对应的微信公众号Id、密钥、微信开发平台Id、密钥、商户号信息等，请修改代码，去除以下代码注释

目录：com.myself.sbdiancan.service.impl.OrderSericeImpl
```
//如果已支付，需要退款
if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
    // 如果配置证书等信息，可去除下行注释
    // payService.refund(orderDTO);
}
```

有关微信支付、扫码登录，请根据腾讯对应开发文档详细配置，配置正常，Demo项目可以正常的运行。

## 日志配置

修改logback-spring.xml文件中的日志生成目录
```
<!--滚动策略-->
<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
    <!--路径-->
    <fileNamePattern>D:/log/sb/info.%d.log</fileNamePattern>
</rollingPolicy>
```

```
<!--滚动策略-->
<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
    <!--路径-->
    <fileNamePattern>D:/log/sb/error.%d.log</fileNamePattern>
</rollingPolicy>
```
将以上的路径改成自己本地的环境

## 缺省文件

resources/static/mp3 中缺少一个MP3文件，运行项目是正常的

## 登录系统

运行系统后，访问：http://127.0.0.1:8080/sb/seller/login?openid=aaa

执行的SQL语句中，存在该管理人员信息，模拟Openid，可以在不配置微信账号等信息下，正常访问（除微信相关功能）

有任何问题，提问：https://github.com/UncleCatMySelf/MintSells/issues 