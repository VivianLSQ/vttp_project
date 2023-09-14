package sg.nus.edu.iss.vttp_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import sg.nus.edu.iss.vttp_project.models.WebSocketChat;

@Component
public class WebSocketListenerService {

    @Autowired
    private SimpMessageSendingOperations msgTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        System.out.println("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {

            WebSocketChat pushNotifications = new WebSocketChat();
            pushNotifications.setType("Approaching Destination");
            pushNotifications.setSender(username);

            msgTemplate.convertAndSend("/user/notifications", pushNotifications);
        }
    }
    
}
