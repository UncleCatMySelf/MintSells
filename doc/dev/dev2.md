# 二次开发文档

更为详细的文档Wiki，还在撰写中，还请在提问区提问，我好及时补充，整理，谢谢支持。

个人能力有限，项目不足之处还请各位朋友多多提建议：https://github.com/UncleCatMySelf/MintSells/issues

以下目录的根目录均为：com/myself/sbdiancan/

## 针对需求

本项目主要针对微信公众号，小程序为主的应用快速开发商城买卖家管理系统，不包含产品前端，管理界面前端为Bootstrap框架，

借助http://www.ibootstrap.cn/ 拖拽方式快速生成H5页面模板，再将代码放置到resources/template中作为模板文件，

这种开发方式对于小团队的后端人员比较便捷，本系统适合小团队，创业团队等作为基础框架快速开发，但系统还存在相应缺点：

对于应用模块不够独立，没有分离开发，各模块存在耦合，且部署运维还不方便。

对于产品模块的数据通过API形式和小程序或移动页面对接，方便快捷，符合当前开发形式。


## 登录登出切面开发

对于登录登出，大家只要实现自己自定义的控制层即可，因为aspect/SellerAuthorizeAspect.java中已经

已经完成了验证，方法实现为通过对Cookie中对应信息的提取与Redis缓存中的信息进行比对，如果是异常登录，会被

handler/SbExceptionHandler.java拦截
```
//拦截登录异常
@ExceptionHandler(value = SbAuthorizeException.class)
@ResponseStatus(HttpStatus.FORBIDDEN)
public ModelAndView handlerAuthorizeException(){
    return new ModelAndView("redirect:"
            .concat(projectUrlConfig.getWechatOpenAuthorize())
            .concat("/sb/wechat/qrAuthorize")
            .concat("?returnUrl=")
            .concat(projectUrlConfig.getSb())
            .concat("/sb/seller/login"));
```
大家可以自定义，拦截后的行为，这里是Demo操作中的跳转扫码登录。controller/SbUserController.java

中的login方法，可以自定义为用户名、密码登录，只需要将随机token放入Redis、Cookie即可。

取消切面，仅需将@Aspect注释即可

## 数据库表无关联

Demo中的数据库是和项目系统没有联系的，大家可以自行选择想要的项目管理设计结构，由于本项目适用小应用，所以建议大家使用JPA。

数据库表推荐使用SQL语句创建，JPA创建存在一定隐患，且慎用@OnetoMany，@ManyToOne，表间关系在sql中定义较好。

创建表后，在dataobject包中，创建对应的数据类，然后在repository中创建对应的Dao，需要实现org.springframework.data.jpa.repository.JpaRepository

第一个参数为数据表对应实体类，第二个参数为Id类型，而对应的Service及Controller就大同小异了。

## 分布式系统设计

这里的分布式系统，是为系统后期的分布式架构做基类，大家可以注意到对于token的存储，我使用了Redis

这也是较为常规的方式，如上图所示，请求先去Redis中查找数据，如果没有再去请求MYSQL数据库，请求到的数据

会更新保存到Redis中，且如果商家对信息做了修改也会对应的更新Redis中的数据。

如controller/BuyerProductController.java中使用了缓存机制，查询时缓存到Redis中

且controller/SbProductController.java中的save方法也做了配置，保存更新后删除Redis缓存数据，

这样下次查询就会重新将新的数据存储到Redis中，对于缓存，请大家根据产品业务与要求使用，请勿乱用。

统一开启关闭缓存配置：SbdiancanApplication.java 的 @EnableCaching

```
//执行存储操作
@Cacheable(cacheNames = "product",key = "123")
//方法执行后，删除存储操作
@CacheEvict(cacheNames = "product",key = "123")
```

## WebSocket前后端通信

WebSocket是客户端使用原生Js写的，放在resources/templates/order/list.ftl中，核心方法是
```
websocket.onmessage = function (event) {
            console.log('收到消息：' + event.data);
            //弹出提醒，播放音乐
            $('#myModal').modal('show')
            //document.getElementById("notice").play();
        }
```
当接收到服务器推送消息时，做出对于的反应，大家可以交给前端，让他去定制。（你懂得~）

需要注意的是，WebSocket连接地址开头不是http，而是ws
```
//判断浏览器是否支持
if ('WebSocket' in window){
    websocket = new WebSocket('ws://127.0.0.1:8080/sb/webSocket');
}else{
    alert('该浏览器不支持WebSocket');
}
```
而后台的实现也相对简单......