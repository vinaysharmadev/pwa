package com.pwa.messenger.repository;

import com.pwa.messenger.models.MessageStatus;
import com.pwa.messenger.models.Msg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgRepository{//} extends CrudRepository<Msg, String> {
    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);



    List<Msg> findByChatId(String chatId);
}