package com.itfang.www.ui;
import com.google.gson.Gson;
import com.itfang.www.dal.po.Message;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author it-fang
 * 聊天室的WebSocket通道.进行客户端与服务器的互动
 */
@ServerEndpoint("/chatSocket")
public class ChatSocket {
    private static Set<ChatSocket> sockets = new HashSet<ChatSocket>();
    private static List<String> names = new ArrayList<String>();
    private Session session;
    private String  username;
    private Gson gson = new Gson();

    /**
     * 获得服务器和客户端之间的传送通道
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void open(Session session) throws IOException {

        this.session = session;
        sockets.add(this);
        String param = URLDecoder.decode(session.getQueryString(),"utf-8");
        this.username = param.substring(param.indexOf("=")+1);
        names.add(this.username);

        Message message = new Message();
        message.setAlert(this.username + "进入聊天室！！");
        message.setDate(new Date().toLocaleString());
        message.setNames(names);

        broadcast(sockets,gson.toJson(message));
    }

    /**
     * 接收客户端发来的消息并将消息推送到聊天室页面
     * @param session
     * @param msg
     * @throws IOException
     */
    @OnMessage
    public void receive(Session session,String msg) throws IOException {
        Message message = new Message();
        message.setSendMsg(msg);
        message.setFrom(this.username);
        message.setDate(new Date().toLocaleString());

        broadcast(sockets,gson.toJson(message));
    }

    /**
     * 关闭客户端和服务器之间的通道
     * @param session
     * @throws IOException
     */
    @OnClose
    public void close(Session session) throws IOException {
        sockets.remove(this);
        names.remove(this.username);

        Message message = new Message();
        message.setAlert(this.username + "退出聊天室！！");
        message.setNames(names);
        message.setDate(new Date().toLocaleString());

        broadcast(sockets,gson.toJson(message));
    }
    /**
     * 聊天室广播功能
     * @param sockets
     * @param msg
     * @throws IOException
     */
    public void broadcast(Set<ChatSocket> sockets, String msg) throws IOException {
        for (Iterator iterator = sockets.iterator(); iterator.hasNext();){
            ChatSocket chatSocket = (ChatSocket) iterator.next();
            chatSocket.session.getBasicRemote().sendText(msg);
        }
    }


}
