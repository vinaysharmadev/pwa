package com.pwa.messenger.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@AllArgsConstructor
@Getter
@Setter
public class CustomMessage {
    boolean addMsg;
    int groupId;
    int msgType;
    int isMyself;
    int status;

    long senderId;
    String receiverId;
    long unixTimestamp;


    String first_name;
    String last_name;
    String senderName;

    Msg message;
}
