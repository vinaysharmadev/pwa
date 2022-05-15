package com.pwa.messenger.controllers;

import com.pwa.messenger.models.CustomMessage;
import com.pwa.messenger.service.ChatMessageService;
import com.pwa.messenger.service.ChatRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @MessageMapping("/sendMessage")
    public void processMessage(@Payload CustomMessage chatMessage) {
        logger.info("Sending message to :" + chatMessage.getReceiverId() + "/queue/messages");
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),"/queue/messages",
                chatMessage);
    }
}
