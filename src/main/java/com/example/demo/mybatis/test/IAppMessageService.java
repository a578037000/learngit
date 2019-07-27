package com.example.demo.mybatis.test;

import java.util.List;

public interface IAppMessageService {

    public List<AppMessage> getMessage();
    public List<AppMessage> getAllMessage();
    public int addMessage(AppMessage appMessage);
    public List<AppMessage> getMessageById(String id);
    public int delMessage(String id);
}
