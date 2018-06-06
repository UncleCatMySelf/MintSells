package com.myself.sbdiancan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket 通信
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 13:40 2018\6\6 0006
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    //定义一个容器
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【WebSocket消息】有新的连接，总数：{}",webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【WebSocket消息】连接断开，总数：{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【WebSocket消息】收到客户端发来的消息：{}",message);
    }

    public void sendMessage(String message){
        for (WebSocket webSocket:webSocketSet){
            log.info("【WebSocket消息】广播消息，message：{}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                log.info("【WebSocket消息】发送消息发生异常，{}",e.getMessage());
            }
        }
    }

}
