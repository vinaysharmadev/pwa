package com.wMessenger.messenger.service;

import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    //As not require to save commenting repo
//    @Autowired
//    private ChatMessageRepository repository;
//    @Autowired
//    private ChatRoomService chatRoomService;

//    public CustomMessage save(CustomMessage chatMessage) {
//        chatMessage.setStatus(1);
//        repository.save(chatMessage);
//        return chatMessage;
//    }
//
//    public long countNewMessages(String senderId, String recipientId) {
//        return repository.countBySenderIdAndRecipientIdAndStatus(
//                senderId, recipientId, MessageStatus.RECEIVED);
//    }
//
//    public List<CustomMessage> findChatMessages(String senderId, String recipientId) {
//        var chatId = chatRoomService.getChatId(senderId, recipientId, false);
//
//        var messages =
//                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
//
////        if(messages.size() > 0) {
////            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
////        }
//
//        return messages;
//    }
//
//    public CustomMessage findById(String id) {
//        return repository
//                .findById(id)
//                .map(chatMessage -> {
//                    chatMessage.setStatus(1);
//                    return repository.save(chatMessage);
//                })
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("can't find message (" + id + ")"));
//    }
}
