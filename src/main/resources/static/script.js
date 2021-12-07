function connect() {
    var socket = new SockJS('/chat-messaging');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {

        console.log("connected: " + frame);
        stompClient.subscribe('/chat/messages', function(response) {
            var data = JSON.parse(response.body)
            draw("left", data.message, data.from);
        });
    });
}

function draw(side, text, name) {
    console.log("drawing...");
    var $message;
    $message = $($('.message_template').clone().html());
    $message.addClass(side).find('.avatar_text').html(name);
    $message.addClass(side).find('.text').html(text);
    $('.messages').append($message);
    return setTimeout(function () {
        return $message.addClass('appeared');
    }, 0);
}
function disconnect(){
    stompClient.disconnect();
}
function sendMessage(){
    $from = $("#input_name").val();
    $mess = $("#message_input_value").val();
    if ($mess !== "") {
        stompClient.send("/app/message", {}, JSON.stringify({'from': $from, 'message': $mess}));
        stompClient.send("/app/save_message", {}, JSON.stringify({'from': $from, 'message': $mess}));
    }

}