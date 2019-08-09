package com.example.demo.ActiceMQbook;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class AyMoodServiceImpl implements  AyMoodService {
    @Resource
    private AyMoodRepository ayMoodRepository;
    @Resource
    private AyMoodProducer ayMoodProducer;
    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    private  static String queue="asyn.save.ay.queue";
    public String asynSave(AyMood ayMood){
        Destination destination = new ActiveMQQueue(queue);
        ayMoodProducer.sendMessage(destination,ayMood);
        return "success";
    }
}
