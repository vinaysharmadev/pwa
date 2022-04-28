package com.wMessenger.messenger.repository;

import com.wMessenger.messenger.models.ChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//As not require to save commenting repo
//@Repository
public interface ChatRoomRepository {//extends CrudRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
