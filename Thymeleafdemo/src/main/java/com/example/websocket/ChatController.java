package com.example.websocket;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	@PreAuthorize ("hasRole('ROLE_USER')")
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public Message greeting(Message message) throws Exception {

		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		message.setUser(authentication.getName());
		message.setDate(new Date());
		
		return message;

	}

}
