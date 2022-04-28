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
        console.log("connected");
        let currentUser = Math.floor(Math.random() * 1000);
        console.log(currentUser);
        stompClient.subscribe(
            "/user/" + currentUser + "/queue/messages",
            onMessageReceived
        );
    }

    function onError(err){
        console.log(err);
    }

    function onMessageReceived(msg){
        let notification =  JSON.parse(msg.body);
         $("#greetings").append("<tr><td>Message from Sender ID </td> <td><b>" + notification.senderId +"</b></td></tr>");
         $("#greetings").append("<tr><td>Sender Name </td> <td><b> " + notification.senderName + "</b></td></tr>");
      }

      function sendMessage(msg){
          if (msg.trim() !== "") {
            const message = {
              senderId: currentUser.id,
              recipientId: activeContact.id,
              senderName: currentUser.name,
              recipientName: activeContact.name,
              content: msg,
              timestamp: new Date(),
            };
            stompClient.send("/app/chat", {}, JSON.stringify(message));

            const newMessages = [...messages];
            newMessages.push(message);
            setMessages(newMessages);
          }
        }


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
debugger;
    stompClient.send("/app/sendMessage", {}, $("#message").val());
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});