package com.example.demo.mybatis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppMessageService implements IAppMessageService {

    @Autowired(required=false)
    private AppMessageMapper appMessageMapper;

    @Override
    public List<AppMessage> getMessage(){
        List<AppMessage> list = new ArrayList<>();
        list.add(appMessageMapper.selectByPrimaryKey("1"));
        //list = mapper.selectAll();
        return list;
    }

    @Override
    public List<AppMessage> getAllMessage(){
        List<AppMessage> list = new ArrayList<>();
        list = appMessageMapper.selectAll();
        return list;
    }

    @Override
    public int addMessage(AppMessage appMessage) {
        return appMessageMapper.insert(appMessage);
    }

    @Override
    public List<AppMessage> getMessageById(String id) {
        return appMessageMapper.getMessById(id);
    }

    @Override
    public int delMessage(String id) {
        return appMessageMapper.deleteByPrimaryKey(id);
    }


}
