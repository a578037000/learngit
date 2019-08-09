package com.example.demo.ActiceMQbook;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AyMoodConsumer {
    @Resource
    private AyMoodService ayMoodService;
   @JmsListener(destination="ay.queue")
    public void receiverQueue(String test){
       System.out.println("用户发表说说{"+test+"}   success！");
    }
    @JmsListener(destination="asyn.save.ay.queue")
    public void receiverQueue(AyMood ayMood){
        ayMoodService.save(ayMood);
    }
}
