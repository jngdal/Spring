package com.example.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "websocket/home";
	}
	@RequestMapping(value="/chat",method=RequestMethod.GET)
	public String chat() {		
		return "websocket/chat";
	}
}
