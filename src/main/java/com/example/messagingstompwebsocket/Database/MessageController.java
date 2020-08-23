package com.example.messagingstompwebsocket.Database;
import com.example.messagingstompwebsocket.api.TranslateText;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController // This means that this class is a Controller

public class MessageController {

    private MessageRepository MessageRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public MessageController(com.example.messagingstompwebsocket.Database.MessageRepository messageRepository) {
        MessageRepository = messageRepository;
    }

    @MessageMapping("chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message,@RequestParam String json) throws IOException, JSONException {

        String jsonString = json; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        String input = obj.getString("mylanguage");
        String out = obj.getString("otherlanguage");
       TranslateText helper;
       helper = new TranslateText();

        String translated = helper.run(message.getMessage(), input, out);
        //System.out.println(translated);
        Message m = new Message();
        message.setMessage(translated);
        message.setId(m.getId());
        message.getCreated();
        MessageRepository.save(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to,message);



    }



}

