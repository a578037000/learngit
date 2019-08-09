package com.example.demo.ActiceMQbook;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class AyMoodProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public  void sendMessage(Destination destination,final String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

    public  void sendMessage(Destination destination,final AyMood ayMood){
        jmsMessagingTemplate.convertAndSend(destination, ayMood);
    }
}
