package ru.mirea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import ru.mirea.entity.Message;
import ru.mirea.repository.MessageRepo;

@Controller
public class DefaultController {
    @Autowired
    MessageRepo messageRepo;

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message getMessages(Message message) {
        return message;
    }

    @Async
    @MessageMapping("/save_message")
    @SendTo("/chat/messages")
    public void saveMessage(Message message){
        System.out.println(message.getMessage());
        Message newMess = new Message();
        newMess.setMessage(message.getMessage());
        newMess.setFrom(message.getFrom());
        messageRepo.save(newMess);
    }
}
