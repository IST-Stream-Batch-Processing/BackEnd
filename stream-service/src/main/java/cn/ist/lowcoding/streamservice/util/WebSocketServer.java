package cn.ist.lowcoding.streamservice.util;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws")
public class WebSocketServer {
    private Session session;

    private static final Map<String, WebSocketServer> webSocketServerMap = new ConcurrentHashMap<>();

    //连接时执行
    @OnOpen
    public void onOpen(Session session) throws IOException {
        webSocketServerMap.put(session.getId(), this);
        this.session = session;
        System.out.println("连接成功! sessionId:" + session.getId());
        System.out.println("Map size:" + webSocketServerMap.size());
        this.session.getBasicRemote().sendText(session.getId());
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        System.out.println("连接关闭！");
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("收到消息！message:" + message);
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("连接错误");
        error.printStackTrace();
    }

    public static WebSocketServer getWebSocketServerBySessionId(String sessionId) {
        return WebSocketServer.webSocketServerMap.get(sessionId);
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}