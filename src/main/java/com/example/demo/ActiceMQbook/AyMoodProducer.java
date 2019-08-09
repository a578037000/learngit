package com.example.demo.ActiceMQbook;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AyMoodProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public  void sendMessage(String queue,final String message){
        jmsMessagingTemplate.convertAndSend(queue,message);
    }

    public  void sendMessage(String queue,final AyMood ayMood){
        jmsMessagingTemplate.convertAndSend(queue,ayMood);
    }
}
