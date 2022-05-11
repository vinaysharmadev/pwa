package com.pwa.messenger.repository;

import com.pwa.messenger.models.CustomMessage;
import com.pwa.messenger.models.MessageStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//As not require to save commenting repo
//@Repository
public interface ChatMessageRepository{ //extends CrudRepository<CustomMessage, String> {
    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<CustomMessage> findByChatId(String chatId);
}
