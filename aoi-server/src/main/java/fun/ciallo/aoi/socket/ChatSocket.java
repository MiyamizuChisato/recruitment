package fun.ciallo.aoi.socket;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.aoi.entity.Chat;
import fun.ciallo.aoi.entity.Relation;
import fun.ciallo.aoi.service.ChatService;
import fun.ciallo.aoi.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/chat/sender/{sender}/receiver/{receiver}")
public class ChatSocket {
    private static ObjectMapper objectMapper;

    private static RelationService relationService;

    private static ChatService chatService;
    private static Map<String, ChatSocket> onlineUsers = new ConcurrentHashMap<>();
    private Session session;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        ChatSocket.objectMapper = objectMapper;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        ChatSocket.chatService = chatService;
    }

    @Autowired
    public void setRelationService(RelationService relationService) {
        ChatSocket.relationService = relationService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sender") int sender, @PathParam(value = "receiver") int receiver) throws IOException {
        this.session = session;
        onlineUsers.put(sender + "-" + receiver, this);
    }

    @OnMessage
    public void onMessage(String chat) throws IOException {
        Chat msg = objectMapper.readValue(chat, Chat.class);
        ChatSocket sendSocket = onlineUsers.get(msg.getSender() + "-" + msg.getReceiver());
        sendSocket.session.getBasicRemote().sendText(chat);
        ChatSocket receiveSocket = onlineUsers.get(msg.getReceiver() + "-" + msg.getSender());
        if (receiveSocket != null) {
            receiveSocket.session.getBasicRemote().sendText(chat);
        } else {
            LambdaQueryWrapper<Relation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Relation::getSender, msg.getReceiver());
            lambdaQueryWrapper.eq(Relation::getReceiver, msg.getSender());
            Relation one = relationService.getOne(lambdaQueryWrapper);
            one.setUnread(one.getUnread() + 1);
            relationService.updateById(one);
            chatService.save(msg);
        }
    }

    @OnClose
    public void onClose(@PathParam(value = "sender") int sender, @PathParam(value = "receiver") int receiver) {
        onlineUsers.remove(sender + "-" + receiver);
    }
}
