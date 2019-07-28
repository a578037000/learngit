package com.example.demo.listener;

import com.example.demo.AyUser;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;



@WebListener
public class AyUserListener implements ServletContextListener {
    @Resource
    private AyUserService ayUserService;
    Logger logger= LogManager.getLogger(this.getClass());
    @Override
    public  void contextInitialized(ServletContextEvent sce) {
        //sce.getServletContext();
        //通过name查询数据
        List<AyUser> localDics =ayUserService.findByName("阿毅");

        System.out.println("findByName()："+localDics.size());
        System.out.println("------------>>> listener init");
        //查询数据库所有的用户
        List<AyUser> ayUserList =ayUserService.findAll();
        //清楚缓存中的用户数据
        logger.info("初始化");
        logger.info("总人数"+ayUserList.size());
    }
    @Override
    public  void contextDestroyed(ServletContextEvent sce) {
        System.out.println("------------>>> listener destroyed");
    }
}
