package com.pwa.messenger.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Msg{

    long msgId;
    String senderName;
    int type;
    int mediaType;
    String text;
    int callerId;
    int isGroupCall;
    String groupName;
    long UNIXTime;
}
