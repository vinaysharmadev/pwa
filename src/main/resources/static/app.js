var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect(){
     var socket = new SockJS('/messenger-connect');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
  }

    function onConnected() {
        let userName = $("#userName").val()
        console.log(userName);
        subscription_path = "/messanger/" + userName + "/queue/messages";
        $("#subscription_path").text(subscription_path);
        stompClient.subscribe(subscription_path, onMessageReceived);
        console.log("connected");

    }

    function onError(err){
        console.log(err);
    }

    function onMessageReceived(msg){
        let notification =  JSON.parse(msg.body);
         $("#greetings").append("<tr><td>Message from Sender ID </td> <td><b>" + notification.senderId +"</b></td></tr>");
         $("#greetings").append("<tr><td>Sender Name </td> <td><b> " + notification.senderName + "</b></td></tr>");
         $("#greetings").append("<tr><td>Message </td> <td><b> " + notification.message.text + "</b></td></tr>");
$("#greetings").append("<hr/>");

      }



function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
let message = '{ "addMsg":  "true", "groupId":  1, "msgType":  1, "isMyself":  1, "status":  1, "senderId": "' + $("#userName").val() +'", "receiverId": "'+$("#receiverId").val()+'", "unixTimestamp": 1212121, "first_name":  "vinay", "last_name":  "kumar", "senderName":  "'+$("#userName").val()+'", "message": {   "msgId": "1",   "senderName": "'+$("#userName").val()+'",   "type": "1",   "mediaType": "1",   "text": "'+ $("#message").val() +'","callerId": "1",   "isGroupCall": "1",   "groupName": "vinay",   "unixtime": "45454"  } }';

    stompClient.send("/messanger/sendMessage", {}, message);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});