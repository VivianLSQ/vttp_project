package sg.nus.edu.iss.vttp_project.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import sg.nus.edu.iss.vttp_project.models.WebSocketChat;

@Controller
public class WebSocketController {

    @MessageMapping("/chat.sendMessage")
	@SendTo("/topic/userFeedback")
	public WebSocketChat sendMessage(@Payload WebSocketChat webSocketChatMsg) {
		return webSocketChatMsg;
	}

	@MessageMapping("/chat.newUser")
	@SendTo("/topic/userFeedback")
	public WebSocketChat newUser(@Payload WebSocketChat webSocketChatMsg,
			SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", webSocketChatMsg.getSender());
		return webSocketChatMsg;
	}

    
}
