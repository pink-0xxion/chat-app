package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {

//    when any message sent to "/app/sendMessage" the messages will broadcasted to "/topic" cannel/group
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        System.out.println("sendMessage of Controller called");
        return message;
    }

    @GetMapping("chat")
    public String chat() {
        return "chat";
    }
}
