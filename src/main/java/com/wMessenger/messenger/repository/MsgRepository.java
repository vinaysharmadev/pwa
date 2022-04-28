package com.wMessenger.messenger.repository;

import com.wMessenger.messenger.models.CustomMessage;
import com.wMessenger.messenger.models.MessageStatus;
import com.wMessenger.messenger.models.Msg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgRepository{//} extends CrudRepository<Msg, String> {
    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);



    List<Msg> findByChatId(String chatId);
}