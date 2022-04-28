package com.wMessenger.messenger.controllers;

import com.wMessenger.messenger.models.CustomMessage;
import com.wMessenger.messenger.models.Greeting;
import com.wMessenger.messenger.models.MessageSegment;
import com.wMessenger.messenger.models.Msg;
import com.wMessenger.messenger.service.ChatMessageService;
import com.wMessenger.messenger.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(MessageSegment message) throws Exception {
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
    }

    @MessageMapping("/sendMessage")
    public void processMessage(@Payload CustomMessage chatMessage) {
        messagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getReceiverId()),"/queue/messages",
                chatMessage);
    }

   /* @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }*/
}
